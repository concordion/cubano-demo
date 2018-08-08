package org.concordion.cubano.template.driver.domain;

import org.concordion.cubano.data.EntityPool;
import org.concordion.cubano.framework.resource.ResourceRegistry;
import org.concordion.cubano.framework.resource.ResourceScope;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Supplies a pool of Users available for test automation and manages usage.
 */
public class UserPool extends EntityPool<User> implements Closeable {

    private final ReportLogger logger = ReportLoggerFactory.getReportLogger(this.getClass().getName());

    public static final User API_USER_PROCESSOR = new User(Role.ROLE1, "autoagent1", "Automation Agent 1");
    public static final User API_USER_MANAGER = new User(Role.ROLE3, "automanager2", "Automation Manager 2");

    private static List<User> pool = new ArrayList<User>();
    private static List<User> poolUsage = Collections.synchronizedList(new ArrayList<User>());
    private List<User> instanceUsage = new ArrayList<User>();

    static {
        pool.add(new User(Role.ROLE1, "autoagent1", "Automation Agent 1"));
        pool.add(new User(Role.ROLE1, "autoagent2", "Automation Agent 2"));
        pool.add(new User(Role.ROLE1, "autoagent3", "Automation Agent 3"));
        pool.add(new User(Role.ROLE1, "autoagent4", "Automation Agent 4"));
        pool.add(new User(Role.ROLE1, "autoagent5", "Automation Agent 5"));
        pool.add(new User(Role.ROLE1, "autoagent6", "Automation Agent 6"));
        pool.add(new User(Role.ROLE1, "autoagent7", "Automation Agent 7"));
        pool.add(new User(Role.ROLE1, "autoagent8", "Automation Agent 8"));
        pool.add(new User(Role.ROLE1, "autoagent9", "Automation Agent 9"));
        pool.add(new User(Role.ROLE1, "autoagent10", "Automation Agent 10"));
        pool.add(new User(Role.ROLE2, "automanager1", "Automation Manager 1"));
        pool.add(new User(Role.ROLE3, "automanager2", "Automation Manager 2"));

    }

    /**
     * Factory method to create new manager user pool manager.
     *
     * @return
     * @param resourceRegistry
     */
    public static UserPool createManager(ResourceRegistry resourceRegistry) {
        UserPool userPool = new UserPool();
        resourceRegistry.registerCloseableResource(userPool, ResourceScope.EXAMPLE);

        return userPool;
    }

    @Override
    protected List<User> getPool() {
        return pool;
    }

    @Override
    protected List<User> getPoolUsage() {
        return poolUsage;
    }

    @Override
    protected List<User> getInstanceUsage() {
        return instanceUsage;
    }

    /**
     * Returns user with requested username. Use with care as this does not care if the user
     * is in use or not.
     * 
     * @param username Requested username
     * @return User if found, otherwise null.
     */
    public static User getUser(String username) {
        username = username.trim();

        for (Object item : pool) {
            User user = (User) item;

            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<User> getUsers() {
        return (List<User>) (List<?>) pool;
    }

    /**
     * Find the first free user with the requested role (starting from random point in list of users), if all are in use then will wait for one to become available.
     * 
     * @param role
     * @return
     */
    public User requestUser(Role role) {
        List<User> filteredUsers = getPool().stream().filter(user -> user.getRole() == role).collect(Collectors.toList());

        return request(filteredUsers, String.format("user with '%s' role to become available", role));
    }

    @Override
    public void release(User user) {
        super.release(user);
    }

    public static boolean hasUsersInUse() {
        return poolUsage.size() > 0;
    }

    public boolean userIsInUse(User user) {
        return poolUsage.contains(user);
    }

    @Override
    public void close() {
        // Do not need to override this method, simply done to enable logging for example
        // Shows how clean up works.
        logger.debug("Before clean up");
        logUserInfo();

        super.close();

        logger.debug("After clean up");
        logUserInfo();
    }

    private void logUserInfo() {
        logger.debug(String.format("Has users in use > '%s'", hasUsersInUse()));

        logger.debug(String.format("Users in Use count > '%s'", getPoolUsage().size()));
        for (User user : getPoolUsage()) {
            logger.debug(String.format("User > '%s'", user.getFullName()));
        }
    }
}
