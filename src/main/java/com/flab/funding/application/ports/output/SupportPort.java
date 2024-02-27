package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.Support;

public interface SupportPort {
    Support saveSupport(Support support);
}
