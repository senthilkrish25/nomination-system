package ae.snoc.nomination.service.impl;

import ae.snoc.nomination.dto.AgreementCodeDTO;
import ae.snoc.nomination.entity.AgreementCodeEntity;
import ae.snoc.nomination.exception.AgreementCodeNotFoundException;
import ae.snoc.nomination.repository.AgreementCodeRepository;
import ae.snoc.nomination.service.AgreementCodeService;
import ae.snoc.nomination.util.AgreementCodeConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Implementation of the AgreementCodeService interface.
 */
@Service
@Slf4j
public class AgreementCodeServiceImpl implements AgreementCodeService {
    private final AgreementCodeRepository agreementCodeRepository;

    @Autowired
    public AgreementCodeServiceImpl(AgreementCodeRepository agreementCodeRepository) {
        this.agreementCodeRepository = agreementCodeRepository;
    }

    /**
     * Creates a new agreement code.
     *
     * @param agreementCodeDTO The DTO containing agreement code information.
     * @return AgreementCodeDTO of the created agreement code.
     * @throws AgreementCodeNotFoundException If the agreement code is already present.
     */

    @Override
    public AgreementCodeDTO createAgreementCode(final AgreementCodeDTO agreementCodeDTO) {
        if (agreementCodeRepository.existsByAgreementCode(agreementCodeDTO.getAgreementCode())) {
            log.error("Error adding Agreement Code.Agreement Code is already present with name: {}", agreementCodeDTO.getAgreementCode());
            throw new AgreementCodeNotFoundException(AgreementCodeConstants.AGREEMENT_CODE_ALREADY_PRESENT +
                    agreementCodeDTO.getAgreementCode());
        } else {
            LocalDate currentDate = LocalDate.now();
            if (agreementCodeDTO.getEffectiveFrom().isAfter(currentDate)
                    && agreementCodeDTO.getEffectiveTo().isAfter(currentDate)
                    && agreementCodeDTO.getEffectiveTo().isAfter(agreementCodeDTO.getEffectiveFrom())) {
                log.info("Creating Agreement");
                AgreementCodeEntity agreementCodeEntity = new AgreementCodeEntity();
                BeanUtils.copyProperties(agreementCodeDTO, agreementCodeEntity);
                agreementCodeRepository.save(agreementCodeEntity);
                BeanUtils.copyProperties(agreementCodeEntity, agreementCodeDTO);
                return agreementCodeDTO;
            } else {
                throw new AgreementCodeNotFoundException(
                        AgreementCodeConstants.EFFECTIVE_FROM_DATE_SHOULD_BE_FUTURE_DATE
                                + AgreementCodeConstants.EFFECTIVE_TO_DATE_SHOULD_BE_FUTURE_DATE
                                + AgreementCodeConstants.EFFECTIVE_TO_DATE_SHOULD_BE_GREATER_THAN_EFFECTIVE_FROM_DATE);
            }
        }
    }

    /**
     * Retrieves an agreement code by its ID.
     *
     * @param id The ID of the agreement code to retrieve.
     * @return AgreementCodeDTO of the retrieved agreement code.
     * @throws AgreementCodeNotFoundException If the agreement code is not found.
     */
    @Override
    public AgreementCodeDTO getAgreementCodeById(Integer id) throws AgreementCodeNotFoundException {
        log.info("Retrieving Agreement Code by Id");
        Optional<AgreementCodeEntity> agreementCodeEntityOptional = agreementCodeRepository.findById(id);
        if (agreementCodeEntityOptional.isEmpty()) {
            log.error("Agreement Code ID is wrong");
            throw new AgreementCodeNotFoundException(AgreementCodeConstants.AGREEMENT_CODE_NOT_FOUND_BY_ID + id);
        } else {
            AgreementCodeDTO agreementCodeDTO = new AgreementCodeDTO();
            BeanUtils.copyProperties(agreementCodeEntityOptional.get(), agreementCodeDTO);
            return agreementCodeDTO;
        }
    }

    /**
     * Retrieves all agreement codes with pagination.
     *
     * @param page     The page number.
     * @param pageSize The page size.
     * @return Page of AgreementCodeDTOs.
     * @throws AgreementCodeNotFoundException If no agreement codes are found.
     */
    @Override
    public Page<AgreementCodeDTO> getAllAgreementCodes(Integer page, Integer pageSize) throws AgreementCodeNotFoundException {
        log.info("Retrieving all the agreement code:");
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AgreementCodeEntity> agreementCodeEntityPage = agreementCodeRepository.findAll(pageable);
        if (!agreementCodeEntityPage.hasContent()) {
            log.error("Agreement Code is Empty:");
            throw new AgreementCodeNotFoundException(AgreementCodeConstants.AGREEMENT_CODE_IS_EMPTY);
        } else {
            return agreementCodeEntityPage.map(agreementCodeEntity -> {
                AgreementCodeDTO agreementCodeDTO = new AgreementCodeDTO();
                BeanUtils.copyProperties(agreementCodeEntity, agreementCodeDTO);
                return agreementCodeDTO;
            });
        }
    }

    /**
     * Updates an existing agreement code.
     *
     * @param agreementCodeDTO The updated DTO.
     * @return AgreementCodeDTO of the updated agreement code.
     * @throws AgreementCodeNotFoundException If the agreement code is not found or already present.
     */
    @Override
    public AgreementCodeDTO updateAgreementCode(final AgreementCodeDTO agreementCodeDTO) throws AgreementCodeNotFoundException {
        log.info("Agreement code is updating:");
        Integer agreementCodeId = agreementCodeDTO.getAgreementId();
        Optional<AgreementCodeEntity> existingAgreementCodeOptional = agreementCodeRepository.findById(agreementCodeId);
        if (existingAgreementCodeOptional.isEmpty()) {
            log.error("Error updating agreement code.Agreement code is Empty:");
            throw new AgreementCodeNotFoundException(AgreementCodeConstants.AGREEMENT_CODE_IS_EMPTY + agreementCodeId);
        } else {
            AgreementCodeEntity existingAgreementCodeEntity = existingAgreementCodeOptional.get();
            if (!existingAgreementCodeEntity.getAgreementCode().equals(agreementCodeDTO.getAgreementCode())
                    && agreementCodeRepository.existsByAgreementCode(agreementCodeDTO.getAgreementCode())) {
                log.error("Error updating agreement code.Agreement code is already present with the same agreement code");
                throw new AgreementCodeNotFoundException(AgreementCodeConstants.AGREEMENT_CODE_ALREADY_PRESENT + agreementCodeDTO.getAgreementCode());
            } else {
                LocalDate currentDate = LocalDate.now();
                if (agreementCodeDTO.getEffectiveFrom().isBefore(agreementCodeDTO.getEffectiveTo())
                        && agreementCodeDTO.getEffectiveTo().isAfter(currentDate)
                        && agreementCodeDTO.getEffectiveTo().isAfter(agreementCodeDTO.getEffectiveFrom())) {
                    BeanUtils.copyProperties(agreementCodeDTO, existingAgreementCodeEntity);
                    AgreementCodeEntity saveAgreementCodeEntity = agreementCodeRepository.save(existingAgreementCodeEntity);
                    BeanUtils.copyProperties(saveAgreementCodeEntity, agreementCodeDTO);
                    log.info("Agreement Code updated successfully for ID:   {}", agreementCodeId);
                    return agreementCodeDTO;
                } else {

                    throw new AgreementCodeNotFoundException(
                            AgreementCodeConstants.EFFECTIVE_FROM_DATE_SHOULD_BE_FUTURE_DATE
                                    + AgreementCodeConstants.EFFECTIVE_TO_DATE_SHOULD_BE_FUTURE_DATE
                                    + AgreementCodeConstants.EFFECTIVE_TO_DATE_SHOULD_BE_GREATER_THAN_EFFECTIVE_FROM_DATE);
                }
            }
        }
    }

    /**
     * Ends an agreement by updating the effectiveTo date for the specified agreement code.
     */
    @Override
    public AgreementCodeDTO endAgreement(Integer id, LocalDate effectiveTo) throws AgreementCodeNotFoundException {
        Optional<AgreementCodeEntity> existingAgreementCodeOptional = agreementCodeRepository.findById(id);

        if (existingAgreementCodeOptional.isEmpty()) {
            log.error("Error ending agreement. Agreement code not found with ID: {}", id);
            throw new AgreementCodeNotFoundException(AgreementCodeConstants.AGREEMENT_CODE_IS_EMPTY + id);
        } else {
            AgreementCodeEntity existingAgreementCodeEntity = existingAgreementCodeOptional.get();
            LocalDate currentDate = LocalDate.now().plusDays(1);

            if (effectiveTo.isBefore(currentDate)
                    || effectiveTo.isBefore(existingAgreementCodeEntity.getEffectiveFrom().plusDays(1))) {
                log.error("Error ending agreement. EffectiveTo date must be a future date.");
                throw new IllegalArgumentException(AgreementCodeConstants.EFFECTIVE_TO_DATE_SHOULD_BE_FUTURE_DATE
                        + AgreementCodeConstants.EFFECTIVE_TO_DATE_SHOULD_BE_GREATER_THAN_EFFECTIVE_FROM_DATE);
            } else {
                existingAgreementCodeEntity.setEffectiveTo(effectiveTo);
                AgreementCodeEntity updatedEntity = agreementCodeRepository.save(existingAgreementCodeEntity);
                AgreementCodeDTO updatedAgreementCodeDTO = new AgreementCodeDTO();
                BeanUtils.copyProperties(updatedEntity, updatedAgreementCodeDTO);
                log.info("Agreement ended successfully for ID: {}", id);
                return updatedAgreementCodeDTO;
            }
        }
    }

    /**
     * Soft deletes an agreement code by marking it as inactive.
     *
     * @param id The ID of the agreement code to delete.
     * @throws AgreementCodeNotFoundException If the agreement code is not found.
     */
    @Override
    public void deleteAgreementCode(Integer id) throws AgreementCodeNotFoundException {
        log.info("Agreement Code is getting deleted");
        Optional<AgreementCodeEntity> agreementCodeEntityOptional = agreementCodeRepository.findById(id);
        if (agreementCodeEntityOptional.isPresent()) {
            log.info("Agreement Code deleted successfully for ID:   " + id);
            AgreementCodeEntity agreementCodeEntity = agreementCodeEntityOptional.get();
            agreementCodeEntity.setIsActive(false);
            agreementCodeRepository.save(agreementCodeEntity);
        } else {
            log.error("Error deleting agreement code for ID:" + id);
            throw new AgreementCodeNotFoundException(AgreementCodeConstants.AGREEMENT_CODE_NOT_FOUND_BY_ID + id);
        }
    }
}
