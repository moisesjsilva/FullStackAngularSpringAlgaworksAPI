package com.jsm.amazon.s3.controller;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsm.amazon.s3.model.AmazonApp;

@Repository
public interface AmazonAppRepository extends JpaRepository<AmazonApp, Long> {
     
}
