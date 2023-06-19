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
import java.lang.Double;
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
public final class Measurement_Table extends ModelAdapter<Measurement> {
  /**
   * Primary Key AutoIncrement
   */
  public static final Property<Integer> id = new Property<Integer>(Measurement.class, "id");

  public static final Property<String> test_name = new Property<String>(Measurement.class, "test_name");

  public static final TypeConvertedProperty<Long, Date> start_time = new TypeConvertedProperty<Long, Date>(Measurement.class, "start_time", true,
  new TypeConvertedProperty.TypeConverterGetter() {
  @Override
  public TypeConverter getTypeConverter(Class<?> modelClass) {
    Measurement_Table adapter = (Measurement_Table) FlowManager.getInstanceAdapter(modelClass);
  return adapter.global_typeConverterDateConverter;
  }
  });

  public static final Property<Double> runtime = new Property<Double>(Measurement.class, "runtime");

  public static final Property<Boolean> is_done = new Property<Boolean>(Measurement.class, "is_done");

  public static final Property<Boolean> is_uploaded = new Property<Boolean>(Measurement.class, "is_uploaded");

  public static final Property<Boolean> is_failed = new Property<Boolean>(Measurement.class, "is_failed");

  public static final Property<String> failure_msg = new Property<String>(Measurement.class, "failure_msg");

  public static final Property<Boolean> is_upload_failed = new Property<Boolean>(Measurement.class, "is_upload_failed");

  public static final Property<String> upload_failure_msg = new Property<String>(Measurement.class, "upload_failure_msg");

  public static final Property<Boolean> is_rerun = new Property<Boolean>(Measurement.class, "is_rerun");

  public static final Property<String> rerun_network = new Property<String>(Measurement.class, "rerun_network");

  public static final Property<String> report_id = new Property<String>(Measurement.class, "report_id");

  public static final Property<Boolean> is_anomaly = new Property<Boolean>(Measurement.class, "is_anomaly");

  public static final Property<String> test_keys = new Property<String>(Measurement.class, "test_keys");

  /**
   * Foreign Key
   */
  public static final Property<Integer> url_id = new Property<Integer>(Measurement.class, "url_id");

  /**
   * Foreign Key
   */
  public static final Property<Integer> result_id = new Property<Integer>(Measurement.class, "result_id");

  public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{id,test_name,start_time,runtime,is_done,is_uploaded,is_failed,failure_msg,is_upload_failed,upload_failure_msg,is_rerun,rerun_network,report_id,is_anomaly,test_keys,url_id,result_id};

  private final DateConverter global_typeConverterDateConverter;

  public Measurement_Table(DatabaseHolder holder, DatabaseDefinition databaseDefinition) {
    super(databaseDefinition);
    global_typeConverterDateConverter = (DateConverter) holder.getTypeConverterForClass(Date.class);
  }

  @Override
  public final Class<Measurement> getModelClass() {
    return Measurement.class;
  }

  @Override
  public final String getTableName() {
    return "`Measurement`";
  }

  @Override
  public final Measurement newInstance() {
    return new Measurement();
  }

  @Override
  public final Property getProperty(String columnName) {
    columnName = QueryBuilder.quoteIfNeeded(columnName);
    switch ((columnName)) {
      case "`id`":  {
        return id;
      }
      case "`test_name`":  {
        return test_name;
      }
      case "`start_time`":  {
        return start_time;
      }
      case "`runtime`":  {
        return runtime;
      }
      case "`is_done`":  {
        return is_done;
      }
      case "`is_uploaded`":  {
        return is_uploaded;
      }
      case "`is_failed`":  {
        return is_failed;
      }
      case "`failure_msg`":  {
        return failure_msg;
      }
      case "`is_upload_failed`":  {
        return is_upload_failed;
      }
      case "`upload_failure_msg`":  {
        return upload_failure_msg;
      }
      case "`is_rerun`":  {
        return is_rerun;
      }
      case "`rerun_network`":  {
        return rerun_network;
      }
      case "`report_id`":  {
        return report_id;
      }
      case "`is_anomaly`":  {
        return is_anomaly;
      }
      case "`test_keys`":  {
        return test_keys;
      }
      case "`url_id`": {
        return url_id;
      }
      case "`result_id`": {
        return result_id;
      }
      default: {
        throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
      }
    }
  }

  @Override
  public final void updateAutoIncrement(Measurement model, Number id) {
    model.id = id.intValue();
  }

  @Override
  public final Number getAutoIncrementingId(Measurement model) {
    return model.id;
  }

  @Override
  public final String getAutoIncrementingColumnName() {
    return "id";
  }

  @Override
  public final ModelSaver<Measurement> createSingleModelSaver() {
    return new AutoIncrementModelSaver<>();
  }

  @Override
  public final void saveForeignKeys(Measurement model, DatabaseWrapper wrapper) {
    if (model.url != null) {
      model.url.save(wrapper);
    }
    if (model.result != null) {
      model.result.save(wrapper);
    }
  }

  @Override
  public final IProperty[] getAllColumnProperties() {
    return ALL_COLUMN_PROPERTIES;
  }

  @Override
  public final void bindToInsertValues(ContentValues values, Measurement model) {
    values.put("`test_name`", model.test_name);
    Long refstart_time = model.start_time != null ? global_typeConverterDateConverter.getDBValue(model.start_time) : null;
    values.put("`start_time`", refstart_time);
    values.put("`runtime`", model.runtime);
    values.put("`is_done`", model.is_done ? 1 : 0);
    values.put("`is_uploaded`", model.is_uploaded ? 1 : 0);
    values.put("`is_failed`", model.is_failed ? 1 : 0);
    values.put("`failure_msg`", model.failure_msg);
    values.put("`is_upload_failed`", model.is_upload_failed ? 1 : 0);
    values.put("`upload_failure_msg`", model.upload_failure_msg);
    values.put("`is_rerun`", model.is_rerun ? 1 : 0);
    values.put("`rerun_network`", model.rerun_network);
    values.put("`report_id`", model.report_id);
    values.put("`is_anomaly`", model.is_anomaly ? 1 : 0);
    values.put("`test_keys`", model.test_keys);
    if (model.url != null) {
      values.put("`url_id`", model.url.id);
    } else {
      values.putNull("`url_id`");
    }
    if (model.result != null) {
      values.put("`result_id`", model.result.id);
    } else {
      values.putNull("`result_id`");
    }
  }

  @Override
  public final void bindToContentValues(ContentValues values, Measurement model) {
    values.put("`id`", model.id);
    bindToInsertValues(values, model);
  }

  @Override
  public final void bindToInsertStatement(DatabaseStatement statement, Measurement model,
      int start) {
    statement.bindStringOrNull(1 + start, model.test_name);
    Long refstart_time = model.start_time != null ? global_typeConverterDateConverter.getDBValue(model.start_time) : null;
    statement.bindNumberOrNull(2 + start, refstart_time);
    statement.bindDouble(3 + start, model.runtime);
    statement.bindLong(4 + start, model.is_done ? 1 : 0);
    statement.bindLong(5 + start, model.is_uploaded ? 1 : 0);
    statement.bindLong(6 + start, model.is_failed ? 1 : 0);
    statement.bindStringOrNull(7 + start, model.failure_msg);
    statement.bindLong(8 + start, model.is_upload_failed ? 1 : 0);
    statement.bindStringOrNull(9 + start, model.upload_failure_msg);
    statement.bindLong(10 + start, model.is_rerun ? 1 : 0);
    statement.bindStringOrNull(11 + start, model.rerun_network);
    statement.bindStringOrNull(12 + start, model.report_id);
    statement.bindLong(13 + start, model.is_anomaly ? 1 : 0);
    statement.bindStringOrNull(14 + start, model.test_keys);
    if (model.url != null) {
      statement.bindLong(15 + start, model.url.id);
    } else {
      statement.bindNull(15 + start);
    }
    if (model.result != null) {
      statement.bindLong(16 + start, model.result.id);
    } else {
      statement.bindNull(16 + start);
    }
  }

  @Override
  public final void bindToStatement(DatabaseStatement statement, Measurement model) {
    int start = 0;
    statement.bindLong(1 + start, model.id);
    bindToInsertStatement(statement, model, 1);
  }

  @Override
  public final void bindToUpdateStatement(DatabaseStatement statement, Measurement model) {
    statement.bindLong(1, model.id);
    statement.bindStringOrNull(2, model.test_name);
    Long refstart_time = model.start_time != null ? global_typeConverterDateConverter.getDBValue(model.start_time) : null;
    statement.bindNumberOrNull(3, refstart_time);
    statement.bindDouble(4, model.runtime);
    statement.bindLong(5, model.is_done ? 1 : 0);
    statement.bindLong(6, model.is_uploaded ? 1 : 0);
    statement.bindLong(7, model.is_failed ? 1 : 0);
    statement.bindStringOrNull(8, model.failure_msg);
    statement.bindLong(9, model.is_upload_failed ? 1 : 0);
    statement.bindStringOrNull(10, model.upload_failure_msg);
    statement.bindLong(11, model.is_rerun ? 1 : 0);
    statement.bindStringOrNull(12, model.rerun_network);
    statement.bindStringOrNull(13, model.report_id);
    statement.bindLong(14, model.is_anomaly ? 1 : 0);
    statement.bindStringOrNull(15, model.test_keys);
    if (model.url != null) {
      statement.bindLong(16, model.url.id);
    } else {
      statement.bindNull(16);
    }
    if (model.result != null) {
      statement.bindLong(17, model.result.id);
    } else {
      statement.bindNull(17);
    }
    statement.bindLong(18, model.id);
  }

  @Override
  public final void bindToDeleteStatement(DatabaseStatement statement, Measurement model) {
    statement.bindLong(1, model.id);
  }

  @Override
  public final String getInsertStatementQuery() {
    return "INSERT INTO `Measurement`(`test_name`,`start_time`,`runtime`,`is_done`,`is_uploaded`,`is_failed`,`failure_msg`,`is_upload_failed`,`upload_failure_msg`,`is_rerun`,`rerun_network`,`report_id`,`is_anomaly`,`test_keys`,`url_id`,`result_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  }

  @Override
  public final String getCompiledStatementQuery() {
    return "INSERT INTO `Measurement`(`id`,`test_name`,`start_time`,`runtime`,`is_done`,`is_uploaded`,`is_failed`,`failure_msg`,`is_upload_failed`,`upload_failure_msg`,`is_rerun`,`rerun_network`,`report_id`,`is_anomaly`,`test_keys`,`url_id`,`result_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  }

  @Override
  public final String getUpdateStatementQuery() {
    return "UPDATE `Measurement` SET `id`=?,`test_name`=?,`start_time`=?,`runtime`=?,`is_done`=?,`is_uploaded`=?,`is_failed`=?,`failure_msg`=?,`is_upload_failed`=?,`upload_failure_msg`=?,`is_rerun`=?,`rerun_network`=?,`report_id`=?,`is_anomaly`=?,`test_keys`=?,`url_id`=?,`result_id`=? WHERE `id`=?";
  }

  @Override
  public final String getDeleteStatementQuery() {
    return "DELETE FROM `Measurement` WHERE `id`=?";
  }

  @Override
  public final String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `Measurement`(`id` INTEGER PRIMARY KEY AUTOINCREMENT, `test_name` TEXT, `start_time` INTEGER, `runtime` REAL, `is_done` INTEGER, `is_uploaded` INTEGER, `is_failed` INTEGER, `failure_msg` TEXT, `is_upload_failed` INTEGER, `upload_failure_msg` TEXT, `is_rerun` INTEGER, `rerun_network` TEXT, `report_id` TEXT, `is_anomaly` INTEGER, `test_keys` TEXT, `url_id` INTEGER, `result_id` INTEGER"+ ", FOREIGN KEY(`url_id`) REFERENCES " + com.raizlabs.android.dbflow.config.FlowManager.getTableName(org.openobservatory.ooniprobe.model.database.Url.class) + "(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION"+ ", FOREIGN KEY(`result_id`) REFERENCES " + com.raizlabs.android.dbflow.config.FlowManager.getTableName(org.openobservatory.ooniprobe.model.database.Result.class) + "(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION" + ");";
  }

  @Override
  public final void loadFromCursor(FlowCursor cursor, Measurement model) {
    model.id = cursor.getIntOrDefault("id");
    model.test_name = cursor.getStringOrDefault("test_name");
    int index_start_time = cursor.getColumnIndex("start_time");
    if (index_start_time != -1 && !cursor.isNull(index_start_time)) {
      model.start_time = global_typeConverterDateConverter.getModelValue(cursor.getLong(index_start_time));
    } else {
      model.start_time = global_typeConverterDateConverter.getModelValue(null);
    }
    model.runtime = cursor.getDoubleOrDefault("runtime");
    int index_is_done = cursor.getColumnIndex("is_done");
    if (index_is_done != -1 && !cursor.isNull(index_is_done)) {
      model.is_done = cursor.getBoolean(index_is_done);
    } else {
      model.is_done = false;
    }
    int index_is_uploaded = cursor.getColumnIndex("is_uploaded");
    if (index_is_uploaded != -1 && !cursor.isNull(index_is_uploaded)) {
      model.is_uploaded = cursor.getBoolean(index_is_uploaded);
    } else {
      model.is_uploaded = false;
    }
    int index_is_failed = cursor.getColumnIndex("is_failed");
    if (index_is_failed != -1 && !cursor.isNull(index_is_failed)) {
      model.is_failed = cursor.getBoolean(index_is_failed);
    } else {
      model.is_failed = false;
    }
    model.failure_msg = cursor.getStringOrDefault("failure_msg");
    int index_is_upload_failed = cursor.getColumnIndex("is_upload_failed");
    if (index_is_upload_failed != -1 && !cursor.isNull(index_is_upload_failed)) {
      model.is_upload_failed = cursor.getBoolean(index_is_upload_failed);
    } else {
      model.is_upload_failed = false;
    }
    model.upload_failure_msg = cursor.getStringOrDefault("upload_failure_msg");
    int index_is_rerun = cursor.getColumnIndex("is_rerun");
    if (index_is_rerun != -1 && !cursor.isNull(index_is_rerun)) {
      model.is_rerun = cursor.getBoolean(index_is_rerun);
    } else {
      model.is_rerun = false;
    }
    model.rerun_network = cursor.getStringOrDefault("rerun_network");
    model.report_id = cursor.getStringOrDefault("report_id");
    int index_is_anomaly = cursor.getColumnIndex("is_anomaly");
    if (index_is_anomaly != -1 && !cursor.isNull(index_is_anomaly)) {
      model.is_anomaly = cursor.getBoolean(index_is_anomaly);
    } else {
      model.is_anomaly = false;
    }
    model.test_keys = cursor.getStringOrDefault("test_keys");
    int index_url_id_Url_Table = cursor.getColumnIndex("url_id");
    if (index_url_id_Url_Table != -1 && !cursor.isNull(index_url_id_Url_Table)) {
      model.url = SQLite.select().from(Url.class).where()
          .and(Url_Table.id.eq(cursor.getInt(index_url_id_Url_Table)))
          .querySingle();
    } else {
      model.url = null;
    }
    int index_result_id_Result_Table = cursor.getColumnIndex("result_id");
    if (index_result_id_Result_Table != -1 && !cursor.isNull(index_result_id_Result_Table)) {
      model.result = new Result();
      model.result.id = cursor.getInt(index_result_id_Result_Table);
    } else {
      model.result = null;
    }
  }

  @Override
  public final boolean exists(Measurement model, DatabaseWrapper wrapper) {
    return model.id > 0
    && SQLite.selectCountOf()
    .from(Measurement.class)
    .where(getPrimaryConditionClause(model))
    .hasData(wrapper);
  }

  @Override
  public final OperatorGroup getPrimaryConditionClause(Measurement model) {
    OperatorGroup clause = OperatorGroup.clause();
    clause.and(id.eq(model.id));
    return clause;
  }
}
