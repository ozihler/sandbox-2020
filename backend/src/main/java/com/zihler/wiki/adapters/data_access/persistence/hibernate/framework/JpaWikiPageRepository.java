package com.zihler.wiki.adapters.data_access.persistence.hibernate.framework;

import org.springframework.data.repository.CrudRepository;

public interface JpaWikiPageRepository extends CrudRepository<WikiPageRow, String> {
}
