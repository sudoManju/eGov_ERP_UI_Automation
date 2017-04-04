package builders.eGovEIS.Attendances;

import entities.requests.eGovEIS.Attendances.Type;

public final class TypeBuilder {
    Type type = new Type();

    public TypeBuilder() {
        type.setId("3");
        type.setCode("P");
    }

    public TypeBuilder withId(String id) {
        type.setId(id);
        return this;
    }

    public TypeBuilder withCode(String code) {
        type.setCode(code);
        return this;
    }

    public Type build() {
        return type;
    }
}
