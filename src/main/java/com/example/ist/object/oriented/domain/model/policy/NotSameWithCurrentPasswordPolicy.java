package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;

public class NotSameWithCurrentPasswordPolicy extends Policy {
    /* private final Hahser hahser */
    private final AuthenticationFactor password;

    public NotSameWithCurrentPasswordPolicy(AuthenticationFactor password /* ,Hashser hahser */) {
        this.password = password;
    }

    @Override
    protected boolean notSatisfiedBy(AuthenticationFactor factor) {
        // 本来は暗号化／ハッシュ化関数をコンストラクタでとり、暗号化／ハッシュ化した値と比較しなければならない
        return this.password.equals(/* hasher.hash( */ factor /* ) */);
    }
}
