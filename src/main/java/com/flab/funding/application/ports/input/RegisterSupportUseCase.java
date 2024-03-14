package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.Support;

public interface RegisterSupportUseCase {

    Support registSupport(Support support);

}
