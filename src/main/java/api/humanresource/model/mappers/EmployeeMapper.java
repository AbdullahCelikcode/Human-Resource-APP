package api.humanresource.model.mappers;

import java.util.HashMap;
import java.util.Map;

public enum EmployeeMapper {
    ID("ID", "id"),
    FIRST_NAME("FIRST_NAME", "firstname"),
    LAST_NAME("LAST_NAME", "lastname"),
    EMAIL("EMAIL", "email"),
    GENDER("GENDER", "gender"),
    ROLE("ROLE", "role"),
    USERNAME("USERNAME", "username"),
    PASSWORD("PASSWORD", "password");
    private final String column;
    private final String field;
    private static final Map<String, String> mapping = new HashMap<>();


    EmployeeMapper(String column, String field) {
        this.column = column;
        this.field = field;

    }

    public String getColumn() {
        return column;
    }

    public String getField() {
        return field;
    }

    static {
        for (EmployeeMapper employee : EmployeeMapper.values()) {
            mapping.put(employee.getColumn(), employee.getField());
        }
    }

    public static Map<String, String> getMapping() {
        return mapping;
    }

}
