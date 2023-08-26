package sosteam.throwapi.domain.store.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreSaveDto {
    private String name;

    private String companyRegistrationNumber;

    private Double latitude;

    private Double longitude;

    private String zipCode;

    private String fullAddress;
}