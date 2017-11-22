package com.becheer.donation.dao;

import com.becheer.donation.model.extension.intention.IntentionDonateExtension;
import org.springframework.stereotype.Component;


@Component
public interface IntentionExtensionMapper {
    int InsertIntention(IntentionDonateExtension intentionDonateExtension);
}
