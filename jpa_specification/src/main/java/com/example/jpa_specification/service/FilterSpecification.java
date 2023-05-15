package com.example.jpa_specification.service;

import com.example.jpa_specification.dto.RequestDto;
import com.example.jpa_specification.dto.SearchRequestDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilterSpecification<T> {
    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchRequestDto.getColumn()),searchRequestDto.getValue());
    }

    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtoList, RequestDto.GlobalOperator globalOperator){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for(SearchRequestDto searchRequestDto: searchRequestDtoList){

                switch (searchRequestDto.getOperation()){
                    case EQUAL:
                        Predicate equal = criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                        predicates.add(equal);
                        break;

                    case LIKE:
                        Predicate like = criteriaBuilder.like(root.get(searchRequestDto.getColumn()), "%" + searchRequestDto.getValue() + "%");
                        predicates.add(like);
                        break;

                    case IN:
                        String[] split = searchRequestDto.getValue().split(",");
                        Predicate in = root.get(searchRequestDto.getColumn()).in(Arrays.asList(split));
                        predicates.add(in);
                        break;

                    case LESS_THAN:
                        Predicate lessThan = criteriaBuilder.lessThan(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                        predicates.add(lessThan);
                        break;

                    case GREATER_THAN:
                        Predicate greaterThan = criteriaBuilder.greaterThan(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                        predicates.add(greaterThan);
                        break;

                    case BETWEEN:
                        String[] split1 = searchRequestDto.getValue().split(",");
                        if (split1.length==2) {
                            Predicate between = criteriaBuilder
                                    .between(root.get(searchRequestDto.getColumn()),
                                            Long.parseLong(split1[0]), Long.parseLong(split1[1]));
                            predicates.add(between);
                        }else
                            throw new IllegalArgumentException("Unexpected value for between. Expected ?,? : " + searchRequestDto.getOperation());
                        break;

                  case JOIN:
                      Predicate join = criteriaBuilder.equal(
                              root.join(searchRequestDto.getJoinTable()).get(searchRequestDto.getColumn())
                              ,searchRequestDto.getValue());
                      predicates.add(join);
                      break;

                    default:
                        throw new IllegalArgumentException("Unexpected value: " + searchRequestDto.getOperation());
                }
            }

            if(globalOperator.equals(RequestDto.GlobalOperator.AND))
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            else
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
