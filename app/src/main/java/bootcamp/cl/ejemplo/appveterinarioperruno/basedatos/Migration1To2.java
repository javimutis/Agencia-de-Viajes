package bootcamp.cl.ejemplo.appveterinarioperruno.basedatos;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
// Se define la migración de la versión 1 a la versión 2 de la base de datos

public class Migration1To2 extends Migration {
    public Migration1To2() {

        super(1, 2);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {

        /*Aquí se deben agregar las sentencias SQL necesarias para actualizar la estructura
        de la base de datos de la versión 1 a la versión 2. Esto puede incluir, por ejemplo,
        agregar o eliminar tablas, modificar campos, etc.

         En este caso, no se han agregado las sentencias SQL específicas, por lo que esta
        migración no haría ninguna modificación a la base de datos.*/
    }
}
