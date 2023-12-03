package api.humanresource.repository.mappers;

import java.util.HashMap;
import java.util.Map;

public enum LeaveMapper {
    ID("ID", "id"),
    START_DATE("START_DATE", "startDate"),
    FINISH_DATE("FINISH_DATE", "finishDate"),
    TYPE("TYPE", "type"),
    EXPLANATION("EXPLANATION", "explanation"),
    EMPLOYEE_ID("EMPLOYEE_ID", "employeeId");
    private final String column;
    private final String field;
    private static final Map<String, String> mapping = new HashMap<>();

    LeaveMapper(String column, String field) {
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
        for (LeaveMapper leaveMapper : LeaveMapper.values()) {
            mapping.put(leaveMapper.getColumn(), leaveMapper.getField());
        }
    }

    public static Map<String, String> getMapping() {
        return mapping;
    }

}

