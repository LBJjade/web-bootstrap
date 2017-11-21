package com.becheer.donation.service;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.intention.IntentionDonateExtension;

public interface IIntentionExtensionService {
    ResponseDto AddIntentionExtension(IntentionDonateExtension intentionDonateExtension);
}
