package example.cubanocore;

import org.concordion.cubano.template.driver.domain.Role;
import org.concordion.cubano.template.driver.domain.User;

import example.ConcordionFixture;

public class UserPoolFixture extends ConcordionFixture {

    private User user;

    public String grabUser() {

        user = getUser();

        return user.getFullName();
    }

    public String grabUserByRole(String roleType) {

        user = userPool().requestUser(Role.valueOf(roleType));

        return String.format("> %s with Role > %s", user.getFullName(), user.getRole());
    }

    public boolean userPoolContainsRequestedUser() {

        user = getUser();

        return userPool().userIsInUse(user);
    }

    public boolean userIsReleasedFromThePool() {

        userPool().release(user);

        return !userPool().userIsInUse(user);
    }

}
