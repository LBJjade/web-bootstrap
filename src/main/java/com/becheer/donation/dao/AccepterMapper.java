package com.becheer.donation.dao;

import com.becheer.donation.model.Accepter;
import com.becheer.donation.model.extension.accepter.AccepterInfoExtension;
import org.springframework.stereotype.Component;

/*
* AccepterMapper
* Creator : xiaokepu
* Date : 2017-11-28
*/

@Component
public interface AccepterMapper {
    Accepter selectAccepterByNo(String acceptorNo);

    AccepterInfoExtension selectAccepterById(Long mid);

    int updateAvator(String avatorUrl, long memberId);
}
