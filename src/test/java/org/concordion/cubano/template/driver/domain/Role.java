package org.concordion.cubano.template.driver.domain;

public enum Role {
    ROLE1("ROLE1"), ROLE2("ROLE2"), ROLE3("ROLE3");

    private String label;

    private Role(String label) {
        this.label = label;
    }

    /**
     * Returns role matching supplied role name.
     * 
     * @param roleName Requested role
     * @return Role
     * @throws IllegalArgumentException if role not found
     */
    public static Role fromRole(String roleName) {
        for (Role role : Role.values()) {
            if (roleName.equalsIgnoreCase(role.toString())) {
                return role;
            }
        }

        throw new IllegalArgumentException(String.format("Role '%s' was not found", roleName));
    }

    public String label() {
        return label;
    }
}