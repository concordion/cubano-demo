package example.cubanocore;

import org.concordion.cubano.template.driver.domain.Role;
import org.concordion.cubano.template.driver.domain.User;

import example.ConcordionFixture;

public class UserPoolFixture extends ConcordionFixture {

    public String grabUser() {

        User user = getUser();

        return user.getFullName();
    }

    public String grabUserByRole() {

        User userRequested = userPool().requestUser(Role.ROLE3);

        return String.format("> %s with Role > %s", userRequested.getFullName(), userRequested.getRole());
    }

    public boolean userPoolContainsRequestedUser() {

        User user = getUser();

        return userPool().userIsInUse(user);
    }

    public boolean userIsReleasedFromThePool() {

        User user = getUser();

        userPool().release(user);

        return !userPool().userIsInUse(user);
    }

}
