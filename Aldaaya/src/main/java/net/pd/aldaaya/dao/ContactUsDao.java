package net.pd.aldaaya.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.pd.aldaaya.common.model.ContactUs;

@Repository
public interface ContactUsDao extends CrudRepository<ContactUs, Long> {


}
