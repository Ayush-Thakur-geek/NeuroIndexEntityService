package com.NeuroIndex.entity.typeConversions;

import com.pgvector.PGvector;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Objects;

public class PGvectorType implements UserType<float[]> {

    @Override
    public int getSqlType() {
        return Types.OTHER;
    }

    @Override
    public Class<float[]> returnedClass() {
        return float[].class;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, float[] value,
                            int index, SharedSessionContractImplementor session)
            throws SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            st.setObject(index, new PGvector(value), Types.OTHER);
        }
    }

    @Override
    public float[] nullSafeGet(ResultSet rs, int position,
                               SharedSessionContractImplementor session,
                               Object owner) throws SQLException {
        String val = rs.getString(position);
        if (val == null) return null;
        return new PGvector(val).toArray();
    }

    @Override
    public boolean equals(float[] x, float[] y) {
        return Arrays.equals(x, y);
    }

    @Override
    public int hashCode(float[] x) {
        return Arrays.hashCode(x);
    }

    @Override
    public float[] deepCopy(float[] value) {
        if (value == null) return null;
        return Arrays.copyOf(value, value.length);
    }

    @Override
    public boolean isMutable() { return true; }

    @Override
    public Serializable disassemble(float[] value) {
        return Arrays.toString(value);
    }

    @Override
    public float[] assemble(Serializable cached, Object owner) {
        try {
            return new PGvector((String) cached).toArray();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}