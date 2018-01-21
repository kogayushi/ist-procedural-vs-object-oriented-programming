package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;
import com.example.ist.object.oriented.domain.model.identity.Password;

public class NotSameWithCurrentPasswordPolicy extends CredentialPolicy<AuthenticationFactor> {
    /* private final Hahser hahser */
    private final AuthenticationFactor password;

    public NotSameWithCurrentPasswordPolicy(Password password /* ,Hashser hahser */) {
        this.password = password;
    }

    @Override
    protected boolean notSatisfiedBy(AuthenticationFactor factor) {
        // 本来は暗号化／ハッシュ化関数をコンストラクタでとり、暗号化／ハッシュ化した値と比較しなければならない
        return /* hasher.hash( */ factor.getValue() /* ) */.equals(this.password.getValue());
    }
}
