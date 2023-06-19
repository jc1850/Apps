package org.openobservatory.ooniprobe.model.database;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.DatabaseHolder;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.converter.DateConverter;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.sql.language.property.TypeConvertedProperty;
import com.raizlabs.android.dbflow.sql.language.property.TypeConvertedProperty.TypeConverterGetter;
import com.raizlabs.android.dbflow.sql.saveable.AutoIncrementModelSaver;
import com.raizlabs.android.dbflow.sql.saveable.ModelSaver;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Number;
import java.lang.Override;
import java.lang.String;
import java.util.Date;

/**
 * This is generated code. Please do not modify
 */
public final class Result_Table extends ModelAdapter<Result> {
  /**
   * Primary Key AutoIncrement
   */
  public static final Property<Integer> id = new Property<Integer>(Result.class, "id");

  public static final Property<String> test_group_name = new Property<String>(Result.class, "test_group_name");

  public static final TypeConvertedProperty<Long, Date> start_time = new TypeConvertedProperty<Long, Date>(Result.class, "start_time", true,
  new TypeConvertedProperty.TypeConverterGetter() {
  @Override
  public TypeConverter getTypeConverter(Class<?> modelClass) {
    Result_Table adapter = (Result_Table) FlowManager.getInstanceAdapter(modelClass);
  return adapter.global_typeConverterDateConverter;
  }
  });

  public static final Property<Boolean> is_viewed = new Property<Boolean>(Result.class, "is_viewed");

  public static final Property<Boolean> is_done = new Property<Boolean>(Result.class, "is_done");

  public static final Property<Long> data_usage_up = new Property<Long>(Result.class, "data_usage_up");

  public static final Property<Long> data_usage_down = new Property<Long>(Result.class, "data_usage_down");

  public static final Property<String> failure_msg = new Property<String>(Result.class, "failure_msg");

  /**
   * Foreign Key
   */
  public static final Property<Integer> network_id = new Property<Integer>(Result.class, "network_id");

  public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{id,test_group_name,start_time,is_viewed,is_done,data_usage_up,data_usage_down,failure_msg,network_id};

  private final DateConverter global_typeConverterDateConverter;

  public Result_Table(DatabaseHolder holder, DatabaseDefinition databaseDefinition) {
    super(databaseDefinition);
    global_typeConverterDateConverter = (DateConverter) holder.getTypeConverterForClass(Date.class);
  }

  @Override
  public final Class<Result> getModelClass() {
    return Result.class;
  }

  @Override
  public final String getTableName() {
    return "`Result`";
  }

  @Override
  public final Result newInstance() {
    return new Result();
  }

  @Override
  public final Property getProperty(String columnName) {
    columnName = QueryBuilder.quoteIfNeeded(columnName);
    switch ((columnName)) {
      case "`id`":  {
        return id;
      }
      case "`test_group_name`":  {
        return test_group_name;
      }
      case "`start_time`":  {
        return start_time;
      }
      case "`is_viewed`":  {
        return is_viewed;
      }
      case "`is_done`":  {
        return is_done;
      }
      case "`data_usage_up`":  {
        return data_usage_up;
      }
      case "`data_usage_down`":  {
        return data_usage_down;
      }
      case "`failure_msg`":  {
        return failure_msg;
      }
      case "`network_id`": {
        return network_id;
      }
      default: {
        throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
      }
    }
  }

  @Override
  public final void updateAutoIncrement(Result model, Number id) {
    model.id = id.intValue();
  }

  @Override
  public final Number getAutoIncrementingId(Result model) {
    return model.id;
  }

  @Override
  public final String getAutoIncrementingColumnName() {
    return "id";
  }

  @Override
  public final ModelSaver<Result> createSingleModelSaver() {
    return new AutoIncrementModelSaver<>();
  }

  @Override
  public final void saveForeignKeys(Result model, DatabaseWrapper wrapper) {
    if (model.network != null) {
      model.network.save(wrapper);
    }
  }

  @Override
  public final IProperty[] getAllColumnProperties() {
    return ALL_COLUMN_PROPERTIES;
  }

  @Override
  public final void bindToInsertValues(ContentValues values, Result model) {
    values.put("`test_group_name`", model.test_group_name);
    Long refstart_time = model.start_time != null ? global_typeConverterDateConverter.getDBValue(model.start_time) : null;
    values.put("`start_time`", refstart_time);
    values.put("`is_viewed`", model.is_viewed ? 1 : 0);
    values.put("`is_done`", model.is_done ? 1 : 0);
    values.put("`data_usage_up`", model.data_usage_up);
    values.put("`data_usage_down`", model.data_usage_down);
    values.put("`failure_msg`", model.failure_msg);
    if (model.network != null) {
      values.put("`network_id`", model.network.id);
    } else {
      values.putNull("`network_id`");
    }
  }

  @Override
  public final void bindToContentValues(ContentValues values, Result model) {
    values.put("`id`", model.id);
    bindToInsertValues(values, model);
  }

  @Override
  public final void bindToInsertStatement(DatabaseStatement statement, Result model, int start) {
    statement.bindStringOrNull(1 + start, model.test_group_name);
    Long refstart_time = model.start_time != null ? global_typeConverterDateConverter.getDBValue(model.start_time) : null;
    statement.bindNumberOrNull(2 + start, refstart_time);
    statement.bindLong(3 + start, model.is_viewed ? 1 : 0);
    statement.bindLong(4 + start, model.is_done ? 1 : 0);
    statement.bindLong(5 + start, model.data_usage_up);
    statement.bindLong(6 + start, model.data_usage_down);
    statement.bindStringOrNull(7 + start, model.failure_msg);
    if (model.network != null) {
      statement.bindLong(8 + start, model.network.id);
    } else {
      statement.bindNull(8 + start);
    }
  }

  @Override
  public final void bindToStatement(DatabaseStatement statement, Result model) {
    int start = 0;
    statement.bindLong(1 + start, model.id);
    bindToInsertStatement(statement, model, 1);
  }

  @Override
  public final void bindToUpdateStatement(DatabaseStatement statement, Result model) {
    statement.bindLong(1, model.id);
    statement.bindStringOrNull(2, model.test_group_name);
    Long refstart_time = model.start_time != null ? global_typeConverterDateConverter.getDBValue(model.start_time) : null;
    statement.bindNumberOrNull(3, refstart_time);
    statement.bindLong(4, model.is_viewed ? 1 : 0);
    statement.bindLong(5, model.is_done ? 1 : 0);
    statement.bindLong(6, model.data_usage_up);
    statement.bindLong(7, model.data_usage_down);
    statement.bindStringOrNull(8, model.failure_msg);
    if (model.network != null) {
      statement.bindLong(9, model.network.id);
    } else {
      statement.bindNull(9);
    }
    statement.bindLong(10, model.id);
  }

  @Override
  public final void bindToDeleteStatement(DatabaseStatement statement, Result model) {
    statement.bindLong(1, model.id);
  }

  @Override
  public final String getInsertStatementQuery() {
    return "INSERT INTO `Result`(`test_group_name`,`start_time`,`is_viewed`,`is_done`,`data_usage_up`,`data_usage_down`,`failure_msg`,`network_id`) VALUES (?,?,?,?,?,?,?,?)";
  }

  @Override
  public final String getCompiledStatementQuery() {
    return "INSERT INTO `Result`(`id`,`test_group_name`,`start_time`,`is_viewed`,`is_done`,`data_usage_up`,`data_usage_down`,`failure_msg`,`network_id`) VALUES (?,?,?,?,?,?,?,?,?)";
  }

  @Override
  public final String getUpdateStatementQuery() {
    return "UPDATE `Result` SET `id`=?,`test_group_name`=?,`start_time`=?,`is_viewed`=?,`is_done`=?,`data_usage_up`=?,`data_usage_down`=?,`failure_msg`=?,`network_id`=? WHERE `id`=?";
  }

  @Override
  public final String getDeleteStatementQuery() {
    return "DELETE FROM `Result` WHERE `id`=?";
  }

  @Override
  public final String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `Result`(`id` INTEGER PRIMARY KEY AUTOINCREMENT, `test_group_name` TEXT, `start_time` INTEGER, `is_viewed` INTEGER, `is_done` INTEGER, `data_usage_up` INTEGER, `data_usage_down` INTEGER, `failure_msg` TEXT, `network_id` INTEGER"+ ", FOREIGN KEY(`network_id`) REFERENCES " + com.raizlabs.android.dbflow.config.FlowManager.getTableName(org.openobservatory.ooniprobe.model.database.Network.class) + "(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION" + ");";
  }

  @Override
  public final void loadFromCursor(FlowCursor cursor, Result model) {
    model.id = cursor.getIntOrDefault("id");
    model.test_group_name = cursor.getStringOrDefault("test_group_name");
    int index_start_time = cursor.getColumnIndex("start_time");
    if (index_start_time != -1 && !cursor.isNull(index_start_time)) {
      model.start_time = global_typeConverterDateConverter.getModelValue(cursor.getLong(index_start_time));
    } else {
      model.start_time = global_typeConverterDateConverter.getModelValue(null);
    }
    int index_is_viewed = cursor.getColumnIndex("is_viewed");
    if (index_is_viewed != -1 && !cursor.isNull(index_is_viewed)) {
      model.is_viewed = cursor.getBoolean(index_is_viewed);
    } else {
      model.is_viewed = false;
    }
    int index_is_done = cursor.getColumnIndex("is_done");
    if (index_is_done != -1 && !cursor.isNull(index_is_done)) {
      model.is_done = cursor.getBoolean(index_is_done);
    } else {
      model.is_done = false;
    }
    model.data_usage_up = cursor.getLongOrDefault("data_usage_up");
    model.data_usage_down = cursor.getLongOrDefault("data_usage_down");
    model.failure_msg = cursor.getStringOrDefault("failure_msg");
    int index_network_id_Network_Table = cursor.getColumnIndex("network_id");
    if (index_network_id_Network_Table != -1 && !cursor.isNull(index_network_id_Network_Table)) {
      model.network = SQLite.select().from(Network.class).where()
          .and(Network_Table.id.eq(cursor.getInt(index_network_id_Network_Table)))
          .querySingle();
    } else {
      model.network = null;
    }
  }

  @Override
  public final boolean exists(Result model, DatabaseWrapper wrapper) {
    return model.id > 0
    && SQLite.selectCountOf()
    .from(Result.class)
    .where(getPrimaryConditionClause(model))
    .hasData(wrapper);
  }

  @Override
  public final OperatorGroup getPrimaryConditionClause(Result model) {
    OperatorGroup clause = OperatorGroup.clause();
    clause.and(id.eq(model.id));
    return clause;
  }
}
