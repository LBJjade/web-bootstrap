package com.becheer.donation.dao;

import com.becheer.donation.model.Accepter;
import com.becheer.donation.model.extension.accepter.AccepterInfoExtension;

/*
* 
* Creator : xiaokepu
* Date : 
*/
public interface AccepterMapper {
    Accepter selectAccepterByNo(String acceptorNo);

    AccepterInfoExtension selectAccepterById(long aid);
}
