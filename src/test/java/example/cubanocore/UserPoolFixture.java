package example.cubanocore;

import example.CubanoDemoFixture;
import org.concordion.cubano.template.driver.domain.Role;
import org.concordion.cubano.template.driver.domain.User;
import org.concordion.cubano.template.driver.domain.UserPool;

public class UserPoolFixture extends CubanoDemoFixture {

    private User user;
    private UserPool userPoolManager;

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

    protected User getUser() {
        if (user == null) {
            user = userPool().requestUser(Role.ROLE1);
        }

        return user;
    }

    public UserPool userPool() {
        if (userPoolManager == null) {
            userPoolManager = UserPool.createManager(this);
        }

        return userPoolManager;
    }
}
