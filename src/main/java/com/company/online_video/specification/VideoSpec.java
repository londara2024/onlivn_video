package com.company.online_video.specification;

import com.company.online_video.entity.Video;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class VideoSpec implements Specification<Video> {

    private final VideoFilter videoFilter;

    List<Predicate> predicates = new ArrayList<Predicate>();

    @Override
    public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (videoFilter.getDescription()!= null) {
            Predicate description = criteriaBuilder.like(criteriaBuilder.upper(root.get("description")), "%" + videoFilter.getDescription() + "%");
            predicates.add(description);
        }
        if (videoFilter.getTitle()!= null) {
            Predicate title = criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), "%" + videoFilter.getTitle() + "%");
            predicates.add(title);
        }
        if (videoFilter.getVideoLink()!= null) {
            Predicate videoLink = criteriaBuilder.like(criteriaBuilder.upper(root.get("videoLink")), "%" + videoFilter.getVideoLink() + "%");
            predicates.add(videoLink);
        }
        if (videoFilter.getImageCover()!= null) {
            Predicate imageCover = criteriaBuilder.like(criteriaBuilder.upper(root.get("imageCover")), "%" + videoFilter.getImageCover() + "%");
            predicates.add(imageCover);
        }
        if (videoFilter.getStatus()!= 0) {
            Predicate status = criteriaBuilder.equal(root.get("status"), videoFilter.getStatus());
            predicates.add(status);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
