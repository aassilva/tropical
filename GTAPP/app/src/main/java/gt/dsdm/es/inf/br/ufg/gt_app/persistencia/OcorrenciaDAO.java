/*
 * Copyright (c) 2018 Marcelo Quinta
 * Copyright (c) 2018 Antonio Silva
 * Copyright (c) 2018 Keslley Lima
 * MIT License.
 */
package gt.dsdm.es.inf.br.ufg.gt_app.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import gt.dsdm.es.inf.br.ufg.gt_app.model.Ocorrencia;

public class OcorrenciaDAO extends SQLiteOpenHelper {

    private static final String DB_NAME = "ocorrencia.db";
    private static final int DB_VERSION = 3;
    private static final String TABLE_OCORRENCIA = "Ocorrencia";

    private static final String ROW_ID = "id";
    private static final String ROW_TITLE = "titulo";
    private static final String ROW_DESCRIPTION = "descricao";
    private static final String ROW_STATUS = "status";
    private static final String ROW_LOCATION = "localizacao";
    private static final String ROW_CATEGORY = "categoria";

    public OcorrenciaDAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "
                + TABLE_OCORRENCIA + "("
                + ROW_ID + " TEXT,"
                + ROW_TITLE + "TEXT,"
                + ROW_DESCRIPTION + " TEXT,"
                + ROW_STATUS + "TEXT,"
                + ROW_LOCATION + "TEXT,"
                + ROW_CATEGORY + "TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    //Melhorar essa classe.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OCORRENCIA);
        onCreate(db);
    }

    public void create(Ocorrencia ocorrencia) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ROW_ID, ocorrencia.getId());
        values.put(ROW_TITLE, ocorrencia.getTitulo());
        values.put(ROW_DESCRIPTION, ocorrencia.getDescricao());
        values.put(ROW_STATUS, ocorrencia.getStatus());
        values.put(ROW_LOCATION, ocorrencia.getLocalizacao());
        values.put(ROW_CATEGORY, ocorrencia.getCategoria());

        db.insert(TABLE_OCORRENCIA, null, values);
        db.close();
    }

    public List<Ocorrencia> getAll() {
        List<Ocorrencia> newsList = new ArrayList<Ocorrencia>();

        String selectQuery = "SELECT * FROM " + TABLE_OCORRENCIA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Ocorrencia ocorrencia = new Ocorrencia(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                        );
                newsList.add(ocorrencia);
            } while (cursor.moveToNext());
        }

        return newsList;
    }

}
