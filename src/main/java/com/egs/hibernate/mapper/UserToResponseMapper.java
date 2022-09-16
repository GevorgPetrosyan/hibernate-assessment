package com.egs.hibernate.mapper;

import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.model.AddressResponseModel;
import com.egs.hibernate.model.PhoneNumberResponseModel;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Tigran Hovhannisyan
 * @Date 16/09/2022
 */
@Component
public class UserToResponseMapper {

    public Set<AddressResponseModel> mapToAddressResponseModelSet(final Set<Address> addresses) {
        final Set<AddressResponseModel> addressResponseModelSet = new HashSet<>();
        addresses.forEach((address -> {
            final AddressResponseModel addressResponseModel = new AddressResponseModel(
                    address.getStreet(),
                    address.getAddressLine1(),
                    address.getAddressLine2(),
                    address.getCity(),
                    address.getPostalCode(),
                    address.getCountry()
            );

            addressResponseModelSet.add(addressResponseModel);
        }));

        return addressResponseModelSet;
    }

    public Set<PhoneNumberResponseModel> mapToPhoneNumberResponseModelSet(final Set<PhoneNumber> phoneNumbers) {
        final Set<PhoneNumberResponseModel> phoneNumberResponseModelsSet = new HashSet<>();

        phoneNumbers.forEach(phoneNumber -> {
            final PhoneNumberResponseModel phoneNumberResponseModel = new PhoneNumberResponseModel(
                    phoneNumber.getPhoneNumber()
            );

            phoneNumberResponseModelsSet.add(phoneNumberResponseModel);
        });

        return phoneNumberResponseModelsSet;
    }
}
