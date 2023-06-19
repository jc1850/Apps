package com.raizlabs.android.dbflow.config;

import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import org.openobservatory.ooniprobe.common.AppDatabase;
import org.openobservatory.ooniprobe.model.database.Measurement_Table;
import org.openobservatory.ooniprobe.model.database.Network_Table;
import org.openobservatory.ooniprobe.model.database.Result_Table;
import org.openobservatory.ooniprobe.model.database.Url_Table;

/**
 * This is generated code. Please do not modify
 */
public final class AppDatabaseAppDatabase_Database extends DatabaseDefinition {
  public AppDatabaseAppDatabase_Database(DatabaseHolder holder) {
    addModelAdapter(new Measurement_Table(holder, this), holder);
    addModelAdapter(new Network_Table(this), holder);
    addModelAdapter(new Result_Table(holder, this), holder);
    addModelAdapter(new Url_Table(this), holder);
    addMigration(3, new AppDatabase.Migration3(org.openobservatory.ooniprobe.model.database.Measurement.class));
    addMigration(2, new AppDatabase.Migration2(org.openobservatory.ooniprobe.model.database.Result.class));
  }

  @Override
  public final Class<?> getAssociatedDatabaseClassFile() {
    return AppDatabase.class;
  }

  @Override
  public final boolean isForeignKeysSupported() {
    return true;
  }

  @Override
  public final boolean backupEnabled() {
    return false;
  }

  @Override
  public final boolean areConsistencyChecksEnabled() {
    return false;
  }

  @Override
  public final int getDatabaseVersion() {
    return 3;
  }

  @Override
  public final String getDatabaseName() {
    return "v2";
  }
}
