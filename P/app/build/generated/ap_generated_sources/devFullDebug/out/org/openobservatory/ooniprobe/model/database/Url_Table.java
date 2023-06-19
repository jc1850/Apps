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
public final class Url_Table extends ModelAdapter<Url> {
  /**
   * Primary Key AutoIncrement
   */
  public static final Property<Integer> id = new Property<Integer>(Url.class, "id");

  public static final Property<String> url = new Property<String>(Url.class, "url");

  public static final Property<String> category_code = new Property<String>(Url.class, "category_code");

  public static final Property<String> country_code = new Property<String>(Url.class, "country_code");

  public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{id,url,category_code,country_code};

  public Url_Table(DatabaseDefinition databaseDefinition) {
    super(databaseDefinition);
  }

  @Override
  public final Class<Url> getModelClass() {
    return Url.class;
  }

  @Override
  public final String getTableName() {
    return "`Url`";
  }

  @Override
  public final Url newInstance() {
    return new Url();
  }

  @Override
  public final Property getProperty(String columnName) {
    columnName = QueryBuilder.quoteIfNeeded(columnName);
    switch ((columnName)) {
      case "`id`":  {
        return id;
      }
      case "`url`":  {
        return url;
      }
      case "`category_code`":  {
        return category_code;
      }
      case "`country_code`":  {
        return country_code;
      }
      default: {
        throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
      }
    }
  }

  @Override
  public final void updateAutoIncrement(Url model, Number id) {
    model.id = id.intValue();
  }

  @Override
  public final Number getAutoIncrementingId(Url model) {
    return model.id;
  }

  @Override
  public final String getAutoIncrementingColumnName() {
    return "id";
  }

  @Override
  public final ModelSaver<Url> createSingleModelSaver() {
    return new AutoIncrementModelSaver<>();
  }

  @Override
  public final IProperty[] getAllColumnProperties() {
    return ALL_COLUMN_PROPERTIES;
  }

  @Override
  public final void bindToInsertValues(ContentValues values, Url model) {
    values.put("`url`", model.url);
    values.put("`category_code`", model.category_code);
    values.put("`country_code`", model.country_code);
  }

  @Override
  public final void bindToContentValues(ContentValues values, Url model) {
    values.put("`id`", model.id);
    bindToInsertValues(values, model);
  }

  @Override
  public final void bindToInsertStatement(DatabaseStatement statement, Url model, int start) {
    statement.bindStringOrNull(1 + start, model.url);
    statement.bindStringOrNull(2 + start, model.category_code);
    statement.bindStringOrNull(3 + start, model.country_code);
  }

  @Override
  public final void bindToStatement(DatabaseStatement statement, Url model) {
    int start = 0;
    statement.bindLong(1 + start, model.id);
    bindToInsertStatement(statement, model, 1);
  }

  @Override
  public final void bindToUpdateStatement(DatabaseStatement statement, Url model) {
    statement.bindLong(1, model.id);
    statement.bindStringOrNull(2, model.url);
    statement.bindStringOrNull(3, model.category_code);
    statement.bindStringOrNull(4, model.country_code);
    statement.bindLong(5, model.id);
  }

  @Override
  public final void bindToDeleteStatement(DatabaseStatement statement, Url model) {
    statement.bindLong(1, model.id);
  }

  @Override
  public final String getInsertStatementQuery() {
    return "INSERT INTO `Url`(`url`,`category_code`,`country_code`) VALUES (?,?,?)";
  }

  @Override
  public final String getCompiledStatementQuery() {
    return "INSERT INTO `Url`(`id`,`url`,`category_code`,`country_code`) VALUES (?,?,?,?)";
  }

  @Override
  public final String getUpdateStatementQuery() {
    return "UPDATE `Url` SET `id`=?,`url`=?,`category_code`=?,`country_code`=? WHERE `id`=?";
  }

  @Override
  public final String getDeleteStatementQuery() {
    return "DELETE FROM `Url` WHERE `id`=?";
  }

  @Override
  public final String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `Url`(`id` INTEGER PRIMARY KEY AUTOINCREMENT, `url` TEXT, `category_code` TEXT, `country_code` TEXT)";
  }

  @Override
  public final void loadFromCursor(FlowCursor cursor, Url model) {
    model.id = cursor.getIntOrDefault("id");
    model.url = cursor.getStringOrDefault("url");
    model.category_code = cursor.getStringOrDefault("category_code");
    model.country_code = cursor.getStringOrDefault("country_code");
  }

  @Override
  public final boolean exists(Url model, DatabaseWrapper wrapper) {
    return model.id > 0
    && SQLite.selectCountOf()
    .from(Url.class)
    .where(getPrimaryConditionClause(model))
    .hasData(wrapper);
  }

  @Override
  public final OperatorGroup getPrimaryConditionClause(Url model) {
    OperatorGroup clause = OperatorGroup.clause();
    clause.and(id.eq(model.id));
    return clause;
  }
}
