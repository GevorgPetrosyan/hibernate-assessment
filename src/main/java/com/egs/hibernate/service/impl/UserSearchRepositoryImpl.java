package com.egs.hibernate.service.impl;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.rest.model.UserSearchRequest;
import com.egs.hibernate.service.UserSearchRepository;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Arman Karapetyan
 * @date 2022-09-09
 **/
public class UserSearchRepositoryImpl implements UserSearchRepository {

    public static final String SORT_BY = "ASC";

    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_FIRSTNAME = "firstName";
    public static final String FIELD_LASTNAME = "lastName";
    public static final String FIELD_BIRTHDATE = "birthdate";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> search(UserSearchRequest request) {

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        final Root<User> userRoot = userCriteriaQuery.from(User.class);

        String format = null;
        int pageNumber = request.getPageNo();
        int pageSize = request.getPageSize();
        final ArrayList<Predicate> predicates = new ArrayList<>();

        if (request.getBirthdate() != null) {
            format = request.getBirthdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        addPredicate(userRoot, criteriaBuilder, predicates, FIELD_USERNAME, request.getUsername());
        addPredicate(userRoot, criteriaBuilder, predicates, FIELD_FIRSTNAME, request.getFirstName());
        addPredicate(userRoot, criteriaBuilder, predicates, FIELD_LASTNAME, request.getLastName());
        addPredicate(userRoot, criteriaBuilder, predicates, FIELD_BIRTHDATE, format);

        if (request.getSortBy().equalsIgnoreCase(SORT_BY)) {
            userCriteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get(request.getSortColumn())));
        } else {
            userCriteriaQuery.orderBy(criteriaBuilder.desc(userRoot.get(request.getSortColumn())));
        }

        userCriteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(userCriteriaQuery).
                setFirstResult((pageNumber - 1) * pageSize).
                setMaxResults(pageSize).getResultList();
    }

    private void addPredicate(Root<User> root, CriteriaBuilder criteriaBuilder,
                              ArrayList<Predicate> predicates, final String fieldName, final String value) {

        if (StringUtils.isBlank(value)) {
            return;
        }

        Predicate concretePredicate;
        concretePredicate = criteriaBuilder.like(root.get(fieldName), "%" + value + "%");
        predicates.add(concretePredicate);

    }

}
