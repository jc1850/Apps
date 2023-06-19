package org.openobservatory.ooniprobe.model.database;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.sql.saveable.AutoIncrementModelSaver;
import com.raizlabs.android.dbflow.sql.saveable.ModelSaver;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Number;
import java.lang.Override;
import java.lang.String;

/**
 * This is generated code. Please do not modify
 */
public final class Network_Table extends ModelAdapter<Network> {
  /**
   * Primary Key AutoIncrement
   */
  public static final Property<Integer> id = new Property<Integer>(Network.class, "id");

  public static final Property<String> network_name = new Property<String>(Network.class, "network_name");

  public static final Property<String> ip = new Property<String>(Network.class, "ip");

  public static final Property<String> asn = new Property<String>(Network.class, "asn");

  public static final Property<String> country_code = new Property<String>(Network.class, "country_code");

  public static final Property<String> network_type = new Property<String>(Network.class, "network_type");

  public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{id,network_name,ip,asn,country_code,network_type};

  public Network_Table(DatabaseDefinition databaseDefinition) {
    super(databaseDefinition);
  }

  @Override
  public final Class<Network> getModelClass() {
    return Network.class;
  }

  @Override
  public final String getTableName() {
    return "`Network`";
  }

  @Override
  public final Network newInstance() {
    return new Network();
  }

  @Override
  public final Property getProperty(String columnName) {
    columnName = QueryBuilder.quoteIfNeeded(columnName);
    switch ((columnName)) {
      case "`id`":  {
        return id;
      }
      case "`network_name`":  {
        return network_name;
      }
      case "`ip`":  {
        return ip;
      }
      case "`asn`":  {
        return asn;
      }
      case "`country_code`":  {
        return country_code;
      }
      case "`network_type`":  {
        return network_type;
      }
      default: {
        throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
      }
    }
  }

  @Override
  public final void updateAutoIncrement(Network model, Number id) {
    model.id = id.intValue();
  }

  @Override
  public final Number getAutoIncrementingId(Network model) {
    return model.id;
  }

  @Override
  public final String getAutoIncrementingColumnName() {
    return "id";
  }

  @Override
  public final ModelSaver<Network> createSingleModelSaver() {
    return new AutoIncrementModelSaver<>();
  }

  @Override
  public final IProperty[] getAllColumnProperties() {
    return ALL_COLUMN_PROPERTIES;
  }

  @Override
  public final void bindToInsertValues(ContentValues values, Network model) {
    values.put("`network_name`", model.network_name);
    values.put("`ip`", model.ip);
    values.put("`asn`", model.asn);
    values.put("`country_code`", model.country_code);
    values.put("`network_type`", model.network_type);
  }

  @Override
  public final void bindToContentValues(ContentValues values, Network model) {
    values.put("`id`", model.id);
    bindToInsertValues(values, model);
  }

  @Override
  public final void bindToInsertStatement(DatabaseStatement statement, Network model, int start) {
    statement.bindStringOrNull(1 + start, model.network_name);
    statement.bindStringOrNull(2 + start, model.ip);
    statement.bindStringOrNull(3 + start, model.asn);
    statement.bindStringOrNull(4 + start, model.country_code);
    statement.bindStringOrNull(5 + start, model.network_type);
  }

  @Override
  public final void bindToStatement(DatabaseStatement statement, Network model) {
    int start = 0;
    statement.bindLong(1 + start, model.id);
    bindToInsertStatement(statement, model, 1);
  }

  @Override
  public final void bindToUpdateStatement(DatabaseStatement statement, Network model) {
    statement.bindLong(1, model.id);
    statement.bindStringOrNull(2, model.network_name);
    statement.bindStringOrNull(3, model.ip);
    statement.bindStringOrNull(4, model.asn);
    statement.bindStringOrNull(5, model.country_code);
    statement.bindStringOrNull(6, model.network_type);
    statement.bindLong(7, model.id);
  }

  @Override
  public final void bindToDeleteStatement(DatabaseStatement statement, Network model) {
    statement.bindLong(1, model.id);
  }

  @Override
  public final String getInsertStatementQuery() {
    return "INSERT INTO `Network`(`network_name`,`ip`,`asn`,`country_code`,`network_type`) VALUES (?,?,?,?,?)";
  }

  @Override
  public final String getCompiledStatementQuery() {
    return "INSERT INTO `Network`(`id`,`network_name`,`ip`,`asn`,`country_code`,`network_type`) VALUES (?,?,?,?,?,?)";
  }

  @Override
  public final String getUpdateStatementQuery() {
    return "UPDATE `Network` SET `id`=?,`network_name`=?,`ip`=?,`asn`=?,`country_code`=?,`network_type`=? WHERE `id`=?";
  }

  @Override
  public final String getDeleteStatementQuery() {
    return "DELETE FROM `Network` WHERE `id`=?";
  }

  @Override
  public final String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `Network`(`id` INTEGER PRIMARY KEY AUTOINCREMENT, `network_name` TEXT, `ip` TEXT, `asn` TEXT, `country_code` TEXT, `network_type` TEXT)";
  }

  @Override
  public final void loadFromCursor(FlowCursor cursor, Network model) {
    model.id = cursor.getIntOrDefault("id");
    model.network_name = cursor.getStringOrDefault("network_name");
    model.ip = cursor.getStringOrDefault("ip");
    model.asn = cursor.getStringOrDefault("asn");
    model.country_code = cursor.getStringOrDefault("country_code");
    model.network_type = cursor.getStringOrDefault("network_type");
  }

  @Override
  public final boolean exists(Network model, DatabaseWrapper wrapper) {
    return model.id > 0
    && SQLite.selectCountOf()
    .from(Network.class)
    .where(getPrimaryConditionClause(model))
    .hasData(wrapper);
  }

  @Override
  public final OperatorGroup getPrimaryConditionClause(Network model) {
    OperatorGroup clause = OperatorGroup.clause();
    clause.and(id.eq(model.id));
    return clause;
  }
}
