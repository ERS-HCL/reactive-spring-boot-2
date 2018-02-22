package com.hcl.tfg.tools.Repository;

import com.hcl.tfg.tools.beans.ConfigValues;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

/**
 * Created by begin.samuel on 19-02-2018.
 */
public interface ConfigValuesRepository extends ReactiveCassandraRepository<ConfigValues, String> {
}
