package de.storchp.fdroidbuildstatus.adapter.fdroid;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class BaseCommandResultTypeAdapter extends TypeAdapter<RunningResult> {

    private final TypeAdapter<JsonElement> jsonElementAdapter;
    private final TypeAdapter<BuildResult> buildCommandResultAdapter;
    private final TypeAdapter<UpdateResult> updateCommandResultTypeAdapter;
    private final TypeAdapter<UnknownResult> unknownCommandResultTypeAdapter;

    public BaseCommandResultTypeAdapter(Gson gson) {
        jsonElementAdapter = gson.getAdapter(JsonElement.class);
        buildCommandResultAdapter = gson.getAdapter(BuildResult.class);
        updateCommandResultTypeAdapter = gson.getAdapter(UpdateResult.class);
        unknownCommandResultTypeAdapter = gson.getAdapter(UnknownResult.class);
    }

    @Override
    public void write(final JsonWriter out, final RunningResult value) {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public RunningResult read(final JsonReader in) throws IOException {
        JsonElement jsonElement = jsonElementAdapter.read(in);
        var subcommand = jsonElement.getAsJsonObject().get("subcommand").getAsString();
        if ("update".equals(subcommand)) {
            return updateCommandResultTypeAdapter.fromJsonTree(jsonElement);
        } else if ("build".equals(subcommand)) {
            return buildCommandResultAdapter.fromJsonTree(jsonElement);
        }
        return unknownCommandResultTypeAdapter.fromJsonTree(jsonElement);
    }

    static class BaseCommandResultTypeAdapterFactory implements TypeAdapterFactory {

        @Override
        public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
            if (type == null) {
                return null;
            }
            Class<?> rawType = type.getRawType();
            if (rawType != RunningResult.class) {
                return null;
            }

            return (TypeAdapter<T>) new BaseCommandResultTypeAdapter(gson);
        }
    }

}