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

import gt.dsdm.es.inf.br.ufg.gt_app.model.Mural;


public class MuralDAO extends SQLiteOpenHelper {

    private static final String DB_NAME = "mural.db";
    private static final int DB_VERSION = 3;
    private static final String TABLE_MURAL = "Mural";

    private static final String ROW_ID = "id";
    private static final String ROW_TITLE = "titulo";
    private static final String ROW_DESCRIPTION = "descricao";
    private static final String ROW_CONTENT = "conteudo";
    private static final String ROW_IMG = "imagem";


    public MuralDAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "
                + TABLE_MURAL + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ROW_TITLE + " TEXT,"
                + ROW_DESCRIPTION + " TEXT,"
                + ROW_CONTENT + "TEXT,"
                + ROW_IMG + "TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }


    //Melhorar essa classe.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MURAL);
        onCreate(db);
    }

    public void create(Mural mural) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ROW_TITLE, mural.getTitulo());
        values.put(ROW_DESCRIPTION, mural.getDescricao());
        values.put(ROW_CONTENT, mural.getConteudo());
        values.put(ROW_IMG, mural.getImagem());

        db.insert(TABLE_MURAL, null, values);
        db.close();
    }

    public List<Mural> getAll() {
        List<Mural> newsList = new ArrayList<Mural>();

        String selectQuery = "SELECT * FROM " + TABLE_MURAL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Mural mural = new Mural();
                mural.setId(Integer.parseInt(cursor.getString(0)));
                mural.setTitulo(cursor.getString(1));
                mural.setDescricao(cursor.getString(2));
                mural.setConteudo(cursor.getString(3));
                mural.setImagem(cursor.getString(4));
                newsList.add(mural);
            } while (cursor.moveToNext());
        } else {
            //MuralWeb mural = new MuralWeb("123");
        }

        return newsList;
    }
}
