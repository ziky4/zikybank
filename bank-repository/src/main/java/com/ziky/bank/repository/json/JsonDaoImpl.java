package com.ziky.bank.repository.json;

import com.fasterxml.jackson.core.*;
import com.ziky.bank.repository.exceptions.JsonDaoException;
import com.ziky.bank.repository.Dao;
import com.ziky.bank.domain.Id;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ziky on 7.12.2014.
 */
public abstract class JsonDaoImpl<T extends Id<V>, V> implements Dao<T> {
    private final JsonFactory jsonFactory;
    private final Path path;
    //protected List<T> entities;

    public JsonDaoImpl() {
        jsonFactory = new JsonFactory();
        path = Paths.get(getPath());
        //entities = new ArrayList<>();
        try {
            init();
            //readFile();
        } catch (JsonDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(T t) {
        //entities.add(t);
        addToRegistry(t.getId(), t);
        writeToFile();
    }

    @Override
    public void delete(T t) {
        //entities.remove(t);
        deleteFromRegistry(t.getId());
        writeToFile();
    }

    @Override
    public void deleteAll() {
        //entities.clear();
        clearRegistry();
        writeToFile();
    }

    @Override
    public T find(Object id) {
        /*for(T entity : entities) {
            if(entity.getId().equals(id)) {
                return entity;
            }
        }

        return null;*/
        return getFromRegistry(id);
    }

    @Override
    public void update(T t) {
        writeToFile();
    }

    protected void init() throws JsonDaoException {
        File directory = path.getParent().toFile();

        if(!directory.exists()) {
            if(!directory.mkdir()) {
                throw new JsonDaoException("Error in creating directory");
            }
        }
    }

    protected void readFile() throws JsonDaoException {
        File file = path.toFile();

        if(file.exists()) {
            try(JsonParser parser = jsonFactory.createParser(file)) {
                JsonToken token = parser.nextToken();

                if(!JsonToken.START_ARRAY.equals(token)) {
                    throw new JsonDaoException("Invalid file structure");
                }

                Map<String, String> tokens = new HashMap<>();

                while(parser.nextToken() == JsonToken.START_OBJECT) {
                    while (parser.nextToken() != JsonToken.END_OBJECT) {
                        parser.nextToken();
                        tokens.put(parser.getCurrentName(), parser.getText());
                    }

                    //entities.add(extract(tokens));
                    T entity = extract(tokens);
                    addToRegistry(entity.getId(), entity);
                    tokens.clear();
                }
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void writeToFile() {
        try (JsonGenerator generator = jsonFactory.createGenerator(path.toFile(), JsonEncoding.UTF8)) {
            Map<String, String> map = new HashMap<>();
            generator.writeStartArray();

            for (T entity : getAll()) {
                bind(entity, map);
                generator.writeStartObject();

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    generator.writeStringField(entry.getKey(), entry.getValue());
                }

                generator.writeEndObject();
                map.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void addToRegistry(Object id, T t);
    protected abstract T getFromRegistry(Object id);
    protected abstract void deleteFromRegistry(Object id);
    protected abstract void clearRegistry();
    protected abstract List<T> getAll();
    protected abstract String getFileName();
    protected abstract T extract(Map<String, String> map);
    protected abstract void bind(T entity, Map<String, String> map);

    private String getPath() {
        return (System.getProperty("user.home") + File.separator + ".bank" + File.separator + getFileName());
    }
}
