package br.com.brainweb.interview.core.features.adapters;

import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

@Converter(autoApply=true)
public class UUIDConverter implements AttributeConverter<UUID, String> {
    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        if(uuid == null) {
            return null; // NOSONAR - be able to persist a null value
        }
        return UUIDTypeDescriptor.ToStringTransformer.INSTANCE.transform(uuid);
    }

    @Override
    public UUID convertToEntityAttribute(String s) {
        if(s == null) {
            return null;
        }
        return UUID.fromString(s);
    }
}
