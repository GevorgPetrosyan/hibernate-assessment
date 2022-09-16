package com.egs.hibernate.mapper;

import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.model.AddressResponseModel;
import com.egs.hibernate.model.PhoneNumberResponseModel;
import com.egs.hibernate.model.UserResponseModel;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vahan Grigoryan
 * @Date 9/9/2022
 */
@Component
public class Mapper {

    /**
     * Map Addresses Set to AddressResponseModels Set
     * @param addresses
     * @return
     */
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

    /**
     * Map PhoneNumbers set to PhoneNumberResponseModels set
     * @param phoneNumbers
     * @return
     */
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
