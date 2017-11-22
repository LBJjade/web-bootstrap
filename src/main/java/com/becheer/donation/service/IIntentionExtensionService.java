package com.becheer.donation.service;

import com.becheer.donation.model.Intention;
import com.becheer.donation.model.base.ResponseDto;

public interface IIntentionExtensionService {
    ResponseDto AddIntentionExtension(Intention intention);
}
