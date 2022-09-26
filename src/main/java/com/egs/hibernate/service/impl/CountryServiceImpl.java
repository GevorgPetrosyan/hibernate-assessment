package com.egs.hibernate.service.impl;

import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.BaseUser;
import com.egs.hibernate.entity.Country;
import com.egs.hibernate.mapper.CountryMapper;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.response.CountriesWithTenKUsersResponse;
import com.egs.hibernate.response.CountryResponse;
import com.egs.hibernate.service.CountryService;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@PersistenceContext
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final EntityManager entityManager;
    private final CountryMapper countryMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void storeAllCountries() {
        if (countryRepository.count() == 0) {
            Arrays.stream(CountryCode.values())
                    .map(it -> Country.builder().countryCode(it).displayName(it.getName()).build())
                    .forEach(countryRepository::save);
        }
    }

    @Override
    public CountriesWithTenKUsersResponse findCountriesWithTenKUsers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CountryCode> query = criteriaBuilder.createQuery(CountryCode.class);
        Root<Country> countryRoot = query.from(Country.class);
        Root<Address> addressRoot = query.from(Address.class);
        Root<BaseUser> userRoot = query.from(BaseUser.class);
        query.select(countryRoot.get("countryCode"));
        query.distinct(true);
        query.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(userRoot.get("id"), addressRoot.get("baseUser")),
                        criteriaBuilder.equal(countryRoot.get("id"), addressRoot.get("country")),
                        criteriaBuilder.isNotNull(addressRoot.get("country"))
                )
        );
        query.groupBy(countryRoot.get("countryCode"))
                .having(criteriaBuilder.ge(criteriaBuilder.count(userRoot.get("id")), 10000));
        List<CountryCode> resultList = entityManager.createQuery(query).getResultList();
        return new CountriesWithTenKUsersResponse(resultList);
    }

    @Override
    public CountryResponse getCountryCodeByDisplayName(String displayName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> query = criteriaBuilder.createQuery(Country.class);
        Root<Country> root = query.from(Country.class);
        query.where(criteriaBuilder.equal(root.get("displayName"), displayName));
        return countryMapper.toResponse(entityManager.createQuery(query).getSingleResult());
    }

    @Override
    public List<CountryCode> getAllCountries() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CountryCode> query = criteriaBuilder.createQuery(CountryCode.class);
        Root<Country> root = query.from(Country.class);
        query.select(root.get("countryCode"));
        return entityManager.createQuery(query).getResultList();
    }

}
