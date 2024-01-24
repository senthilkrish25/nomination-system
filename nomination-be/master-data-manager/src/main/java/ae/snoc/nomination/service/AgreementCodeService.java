package ae.snoc.nomination.service;

import ae.snoc.nomination.dto.AgreementCodeDTO;
import ae.snoc.nomination.exception.AgreementCodeNotFoundException;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface AgreementCodeService {
    AgreementCodeDTO createAgreementCode(final AgreementCodeDTO agreementCodeDTO);

    AgreementCodeDTO getAgreementCodeById(final Integer id) throws AgreementCodeNotFoundException;

    Page<AgreementCodeDTO> getAllAgreementCodes(final Integer page, final Integer pageSize) throws AgreementCodeNotFoundException;

    AgreementCodeDTO updateAgreementCode(final AgreementCodeDTO agreementCodeDTO) throws AgreementCodeNotFoundException;

    AgreementCodeDTO endAgreement(final Integer id, final LocalDate effectiveTo) throws AgreementCodeNotFoundException;

    void deleteAgreementCode(final Integer id) throws AgreementCodeNotFoundException;
}
