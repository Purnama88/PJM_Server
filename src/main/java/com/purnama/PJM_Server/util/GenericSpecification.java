/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author p_cor
 * @param <T>
 */
public class GenericSpecification<T> implements Specification<T> {
    
    
    private SearchCriteria searchCriteria;
   
    public GenericSpecification(final SearchCriteria searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }
    
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        switch (searchCriteria.getOperation()) {
                case GREATER_THAN:
                    return criteriaBuilder.greaterThan(
                            root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
                    
                case LESS_THAN:
                    return criteriaBuilder.lessThan(
                            root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
                    
                case GREATER_THAN_EQUAL:
                    return criteriaBuilder.greaterThanOrEqualTo(
                            root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
                    
                case LESS_THAN_EQUAL:
                    return criteriaBuilder.lessThanOrEqualTo(
                            root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
                    
                case NOT_EQUAL:
                    return criteriaBuilder.notEqual(
                            root.get(searchCriteria.getKey()), searchCriteria.getValue());
                    
                case EQUAL:
                    return criteriaBuilder.equal(
                            root.get(searchCriteria.getKey()), searchCriteria.getValue());
                    
                case MATCH:
                    return criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(searchCriteria.getKey())),
                            "%" + searchCriteria.getValue().toString().toLowerCase() + "%");
                    
                case MATCH_END:
                    return criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(searchCriteria.getKey())),
                            searchCriteria.getValue().toString().toLowerCase() + "%");
                    
                default:
                    return null;
        }
    }
}