package ae.snoc.nomination.controller;

import ae.snoc.nomination.dto.AgreementCodeDTO;
import ae.snoc.nomination.exception.AgreementCodeNotFoundException;
import ae.snoc.nomination.payload.ResponsePayload;
import ae.snoc.nomination.service.AgreementCodeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;

/**
 * Controller class for managing agreement codes.
 */
@RestController
@RequestMapping("/agreement")
@Slf4j
public class AgreementCodeController {
    private final AgreementCodeService agreementCodeService;

    @Autowired
    public AgreementCodeController(AgreementCodeService agreementCodeService) {
        this.agreementCodeService = agreementCodeService;
    }

    /**
     * Endpoint to create a new agreement code.
     *
     * @param agreementCodeDTO The agreement code DTO to be created.
     * @return ResponseEntity with the created agreement code DTO and HTTP status.
     * @throws AgreementCodeNotFoundException If the agreement code is not found.
     */
    @PostMapping
    public ResponseEntity<ResponsePayload> createAgreementCode(@Valid @RequestBody AgreementCodeDTO agreementCodeDTO)
            throws AgreementCodeNotFoundException {
        log.info("add agreement endpoint is invoked");
        final AgreementCodeDTO saveAgreementCodeDto = agreementCodeService.createAgreementCode(agreementCodeDTO);
        return new ResponseEntity<>(ResponsePayload.create(saveAgreementCodeDto, null, HttpStatus.CREATED, "Created"), HttpStatus.CREATED);
    }

    /**
     * Endpoint to get an agreement code by its ID.
     *
     * @param id The ID of the agreement code to retrieve.
     * @return ResponseEntity with the retrieved agreement code DTO and HTTP status.
     * @throws AgreementCodeNotFoundException If the agreement code is not found.
     */
    @GetMapping("/byId/{id}")
    public ResponseEntity<ResponsePayload> getAgreementCodeById(@PathVariable final Integer id) throws AgreementCodeNotFoundException {
        log.info("Get Agreement Code by id Service is running");
        final AgreementCodeDTO agreementCodeDTO = agreementCodeService.getAgreementCodeById(id);
        return ResponseEntity.ok(ResponsePayload.create(agreementCodeDTO, null, HttpStatus.OK, "Success"));
    }

    /**
     * Endpoint to get all agreement codes with pagination.
     *
     * @param page     The page number (default is 0).
     * @param pageSize The page size (default is 10).
     * @return ResponseEntity with the paginated agreement codes and HTTP status.
     * @throws AgreementCodeNotFoundException If no agreement codes are found.
     */
    @GetMapping
    public ResponseEntity<ResponsePayload> getAllAgreementCode(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "10") final Integer pageSize

    ) throws AgreementCodeNotFoundException {
        log.info("getting all agreement code endpoint is invoked");
        final Page<AgreementCodeDTO> agreementCodeDTOPage = agreementCodeService.getAllAgreementCodes(page, pageSize);
        return ResponseEntity.ok(ResponsePayload.create(agreementCodeDTOPage, null, HttpStatus.OK, "Success"));
    }

    /**
     * Endpoint to update an agreement code by its ID.
     *
     * @param agreementCodeDTO The updated agreement code DTO.
     * @return ResponseEntity with the updated agreement code DTO and HTTP status.
     * @throws AgreementCodeNotFoundException If the agreement code is not found.
     */
    @PutMapping
    public ResponseEntity<ResponsePayload> updateAgreementCode(@Valid @RequestBody final AgreementCodeDTO agreementCodeDTO) throws AgreementCodeNotFoundException {
        log.info("update agreement code is invoked: ");
        AgreementCodeDTO saveAgreementCodeDTO = agreementCodeService.updateAgreementCode(agreementCodeDTO);
        return ResponseEntity.ok(ResponsePayload.create(saveAgreementCodeDTO, null, HttpStatus.OK, "Updated"));
    }

    /**
     * Endpoint to end an agreement by updating the effectiveTo date to a future date.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ResponsePayload> endAgreement(@PathVariable final Integer id,
                                                        @RequestBody Map<String, LocalDate> requestBody)
            throws AgreementCodeNotFoundException {
        log.info("Ending agreement with ID: {}", id);
        LocalDate effectiveTo = requestBody.get("effectiveTo");
        if (effectiveTo == null) {
            return ResponseEntity.badRequest().body(ResponsePayload.create(null, "EffectiveTo is required", HttpStatus.BAD_REQUEST, "Bad Request"));
        }
        AgreementCodeDTO updatedAgreementCodeDTO = agreementCodeService.endAgreement(id, effectiveTo);
        return ResponseEntity.ok(ResponsePayload.create(updatedAgreementCodeDTO, null, HttpStatus.OK, "Agreement updated successfully: "));
    }

    /**
     * Endpoint to delete an agreement code by its ID.
     *
     * @param id The ID of the agreement code to delete.
     * @return ResponseEntity with HTTP status indicating success or failure.
     * @throws AgreementCodeNotFoundException If the agreement code is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePayload> deleteAgreementCode(@PathVariable final Integer id) throws AgreementCodeNotFoundException {
        log.info("deleting agreement code endpoint is invoked: ");
        agreementCodeService.deleteAgreementCode(id);
        return new ResponseEntity<>(ResponsePayload.create(null, null, HttpStatus.OK, "Agreement is Deleted: "), HttpStatus.OK);
    }
}