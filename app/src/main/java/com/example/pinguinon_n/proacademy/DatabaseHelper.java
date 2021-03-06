package com.example.pinguinon_n.proacademy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "DB2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_BOOKS);
        db.execSQL(TABLE_PACKAGES);
        db.execSQL(TABLE_TEXTS);
        db.execSQL(TABLE_QUESTION);
        db.execSQL(TABLE_ANSWERGOT);
        db.execSQL(INSERT_BOOKS);
        db.execSQL(INSERT_PACKS);
        db.execSQL(INSERT_TEXTS);
        db.execSQL(INSERT_QUESTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_BOOKS);
        db.execSQL(DELETE_PACKAGES);
        db.execSQL(DELETE_TEXTS);
        db.execSQL(DELETE_QUESTION);
        db.execSQL(DELETE_ANSWERGOT);
        db.execSQL(TABLE_BOOKS);
        db.execSQL(TABLE_PACKAGES);
        db.execSQL(TABLE_TEXTS);
        db.execSQL(TABLE_QUESTION);
        db.execSQL(TABLE_ANSWERGOT);
        db.execSQL(INSERT_BOOKS);
        db.execSQL(INSERT_PACKS);
        db.execSQL(INSERT_TEXTS);
        db.execSQL(INSERT_QUESTIONS);
    }

    public void openDatabase() {
        this.getWritableDatabase();
    }

    public void closeDatabase() {
        this.close();
    }

    private static final String DELETE_BOOKS = "DROP TABLE IF EXISTS books ;";
    private static final String DELETE_TEXTS = "DROP TABLE IF EXISTS texts ;";
    private static final String DELETE_QUESTION = "DROP TABLE IF EXISTS question;";
    private static final String DELETE_ANSWERGOT = "DROP TABLE IF EXISTS answergot;";
    private static final String DELETE_PACKAGES = "DROP TABLE IF EXISTS packs;";

    private static final String TABLE_BOOKS =
            "CREATE TABLE books(" +
            "id_book INTEGER PRIMARY KEY NOT NULL," +
            "book TEXT NOT NULL," +
            "author TEXT NOT NULL" +
            ");";
    private static final String TABLE_TEXTS =
            "CREATE TABLE texts(" +
            "id_text INTEGER PRIMARY KEY NOT NULL," +
            "content TEXT NOT NULL," +
            "leveltxt INTEGER NOT NULL," +
            "nbook INTEGER NOT NULL," +
            "package INTEGER NOT NULL,"+
            "FOREIGN KEY (nbook) REFERENCES books(id_book)" +
            "FOREIGN KEY(package) REFERENCES packs(id_pack)" +
            ");";
    private static final String TABLE_QUESTION =
            "CREATE TABLE question(" +
            "id_question INTEGER PRIMARY KEY NOT NULL," +
            "content TEXT NOT NULL," +
            "ntext INTEGER NOT NULL," +
            "ans1 TEXT NOT NULL," +
            "ans2 TEXT NOT NULL," +
            "ans3 TEXT NOT NULL," +
            "ans4 TEXT NOT NULL," +
            "FOREIGN KEY(ntext) REFERENCES texts(id_text)" +
            ");";
    private static final String TABLE_ANSWERGOT =
            "CREATE TABLE answergot(" +
            "id_ansgot INTEGER PRIMARY KEY NOT NULL," +
            "nquestion INTEGER NOT NULL," +
            "score INTEGER NOT NULL," +
            "FOREIGN KEY(nquestion) REFERENCES question(id_question));";
    private static final String TABLE_PACKAGES =
            "CREATE TABLE packs(" +
            "id_pack INTEGER PRIMARY KEY NOT NULL," +
            "descr VARCHAR NOT NULL" +
            ");";

    private static final String INSERT_PACKS=
            "INSERT INTO packs(descr) VALUES('paquete inicial');";

    private static final String INSERT_BOOKS =
            "INSERT INTO books(id_book,book,author) VALUES (1,'Después de ti','Jojo Moyes'), (2,'Cinco esquinas','Mario Vargas Llosa'), (3,'Historia de un canalla','Julia Navarro'), (4,'Patria','Fernando Aramburu'), (5,'El monarca de las sombras','Javier Cercas'), (6,'El laberinto de los espíritus','Carlos Ruiz Zafón'), (7,'Como fuego en el hielo','Luz Gabás'), (8,'Las ventanas del cielo','Gonzalo Giner'), (9,'Restos mortales','Donna Leon'), (10,'El principito','ANTOINE DE SAINT-EXUPÉRY'), (11,'El asesinato de Sócrates','Marcos Chicot'), (12,'Tres veces tú','Federico Moccia'), (13,'La pareja de a lado','Shari Lapena'), (14,'Legado en los huesos','Dolores Redondo'), (15,'Los herederos de la tierra','Ildefonso Falcones');";
    private static final String INSERT_TEXTS =
            "INSERT INTO texts(id_text,content,leveltxt,nbook,package) VALUES (1,'El hombre grande en el extremo de la barra está sudando. Sostiene la cabeza baja sobre su doble whisky y cada pocos minutos él levanta la mirada hacia la puerta, y un fino brillo de sudor aflora en la tira de luces. Deja escapar una larga y temblorosa respiración disfrazada como un suspiro y vuelve de nuevo a su bebida.\n—Hey, ¿Disculpe?\nLevanto la mirada del fregadero\n— ¿Puede traerme otro trago? \nQuiero decirle que realmente no es una buena idea, que eso no ayudará. Que incluso puede llevarlo al límite. Pero él es un tipo grande y faltan quince minutos hasta la hora del cierre, y de acuerdo con las directrices de la empresa, no tengo ninguna razón para decirle que no. Así que camino, tomo su vaso y lo sostengo hasta mi ojo. Él asiente con la cabeza en la botella.',1,1,1)," +
                    "(2,'¿Había despertado o seguía soñando? Aquel calorcito en su empeine derecho estaba siempre allí, una sensación insólita que le erizaba todo el cuerpo y le revelaba que no estaba sola en esa cama. Los recuerdos acudían en tropel a su cabeza pero se iban ordenando como un crucigrama que se llena lentamente. Habían estado divertidas y algo achispadas por el vino después de la comida, pasando del terrorismo a las películas y a los chismes sociales, cuando, de pronto, Chabela miró el reloj y se puso de pie de un salto, pálida: «¡El toque de queda! ¡Dios mío, ya no me da tiempo a llegar a La Rinconada! Cómo se nos ha pasado la hora». Marisa insistió para que se quedara a dormir con ella. No habría problema, Quique había partido a Arequipa por el directorio de mañana temprano en la cervecería, eran dueñas del departamento del Golf. Chabela llamó a su marido. Luciano, siempre tan comprensivo, dijo que no había inconveniente, él se encargaría de que las dos niñas salieran puntualmente a tomar el ómnibus del colegio.',2,2,1)," +
                    "(3,'Tendría siete u ocho años, y caminaba junto a la mujer que cuidaba de mí y de mi hermano. Debía de ser media tarde, hora en que salíamos del colegio. Estaba de malhumor porque la maestra me había regañado por haberme distraído mientras explicaba no sé qué. \nMi hermano iba agarrado de la mano de María, pero yo prefería caminar a mi ritmo. Además, a María le sudaban las palmas y me molestaba el contacto de su piel húmeda sobre la mía.\nYo corría de un lado a otro ignorando las quejas de María. —Se lo voy a decir a tu madre. Todos los días me haces lo mismo, te sueltas de mi mano y lo peor es que ni siquiera dejas que te agarre cuando cruzamos de una calle a otra, y no miras nunca si viene un coche. Un día va a pasar algo.',3,3,1)," +
                    "(4,'Ahí va la pobre, a romperse en él. Lo mismo que se rompe una ola en las rocas. Un poco de espuma y adiós. ¿No ve que ni siquiera se toma la molestia de abrirle la puerta? Sometida, más que sometida. Y esos zapatos de tacón y esos labios rojos a sus cuarenta y cinco años, ¿para qué? Con tu categoría, hija, con tu posición y tus estudios, ¿qué te lleva a comportarte como una adolescente? Si el aita levantara la cabeza... En el momento de subir al coche, Nerea dirigió la vista hacia la ventana tras cuyo visillo supuso que su madre, como de costumbre, estaría observándola. Y sí, aunque ella no pudiese verla desde la calle, Bittori la estaba mirando con pena y con el entrecejo arrugado, y hablaba a solas y susurró diciendo ahí va la pobre, de adorno de ese vanidoso a quien nunca se le ha pasado por la cabeza hacer feliz a nadie. ¿No se da cuenta de que una mujer ha de estar muy desesperada para tratar de seducir a su marido después de doce años de matrimonio? En el fondo es mejor que no hayan tenido descendencia. Nerea agitó brevemente la mano en señal de despedida antes de meterse dentro del taxi. Su madre, en el tercer piso, oculta tras el visillo, desvió la mirada. Se veía una amplia franja de mar por encima de los tejados, el faro de la isla de Santa Clara, nubes tenues a lo lejos. La mujer del tiempo había anunciado sol. Y ella, ay, qué vieja me estoy haciendo, volvió a mirar la calle y el taxi ya se había perdido de vista. ',4,4,1)," +
                    "(5,'Se llamaba Manuel Mena y murió a los diecinueve años en la batalla del Ebro. Fue el 21 de septiembre de 1938, hacia el final de la guerra civil, en un pueblo catalán llamado Bot. Era un franquista entusiasta, o por lo menos un entusiasta falangista, o por lo menos lo fue al principio de la guerra: en esa época se alistó en la 3.ª Bandera de Falange de Cáceres, y al año siguiente, recién obtenido el grado de alférez provisional, lo destinaron al Primer Tabor de Tiradores de Ifni, una unidad de choque perteneciente al cuerpo de Regulares. Doce meses más tarde murió en combate, y durante años fue el héroe oficial de mi familia. Era tío paterno de mi madre, que desde niño me ha contado innumerables veces su historia, o más bien su historia y su leyenda, de tal manera que antes de ser escritor yo pensaba que alguna vez tendría que escribir un libro sobre él. Lo descarté precisamente en cuanto me hice escritor; la razón es que sentía que Manuel Mena era la cifra exacta de la herencia más onerosa de mi familia, y que contar su historia no sólo equivalía a hacerme cargo de su pasado político sino también del pasado político de toda mi familia, que era el pasado que más me abochornaba; no quería hacerme cargo de eso, no veía ninguna necesidad de hacerlo, y mucho menos de airearlo en un libro bastante tenía con aprender a vivir con ello. ',5,5,1)," +
                    "(6,'Aquella noche soñé que regresaba al Cementerio de los Libros Olvidados. Volvía a tener diez años y despertaba en mi antiguo dormitorio para sentir que la memoria del rostro de mi madre me había abandonado. Y del modo en que se saben las cosas en los sueños, sabía que la culpa era mía y solo mía porque no merecía recordarlo y porque no había sido capaz de hacerle justicia. Al poco entraba mi padre, alertado por mis gritos de angustia. Mi padre, que en mi sueño todavía era joven y aún guardaba todas las respuestas del mundo, me abrazaba para consolarme. Luego, cuando las primeras luces pintaban una Barcelona de vapor, salíamos a la calle. Mi padre, por algún motivo que yo no acertaba a comprender, solo me acompañaba hasta el portal. Allí me soltaba la mano y me daba a entender que aquel era un viaje que debía hacer yo solo. Echaba a caminar, pero recuerdo que me pesaban la ropa, los zapatos y hasta la piel. Cada paso que daba requería más esfuerzo que el anterior. Al llegar a las Ramblas advertía que la ciudad había quedado suspendida en un instante infinito. Las gentes habían detenido el paso y aparecían congeladas como figuras en una vieja fotografía. Una paloma que alzaba el vuelo dibujaba apenas el esbozo borroso de un batir de alas. Briznas de polen flotaban inmóviles en el aire como luz en polvo. El agua de la fuente de Canaletas brillaba en el vacío y parecía un collar de lágrimas de cristal. Lentamente, como si intentara caminar bajo el agua, conseguía adentrarme en el conjuro de aquella Barcelona detenida en el tiempo hasta llegar al umbral del Cementerio de los Libros Olvidados. Una vez allí me detenía, exhausto. No acertaba a comprender qué era aquella carga invisible que arrastraba conmigo y que casi no me permitía moverme. ',6,6,1)," +
                    "(7,'—Todavía estás a tiempo de solucionar este asunto de manera pacífica… Attua ya no sabía qué más hacer para convencer a Matías de que aquello era una locura. Le había repetido la misma frase docenas de veces en los últimos minutos, intentando ganar tiempo para que se despejase de los efectos del alcohol y se diese cuenta de que había que deshacer el entuerto como fuera. Él mismo se sentía aturdido y nervioso. Hacía apenas un par de horas estaba riéndose en la taberna y ahora, en los tibios momentos antes del amanecer, era el padrino de un improvisado duelo en el que podía perder a su mejor amigo. —¡No pienso echarme atrás! —repitió Matías con voz un tanto pastosa. Por mucho que intentara sonar firme, no podía disimular su miedo—. ¡Yo no soy ningún cobarde! ¿Qué se ha creído el muy insolente? —Esto va en serio —dijo Attua exasperado—. No es un juego de niños. No estás reventando vejigas de cordero llenas de agua para enfadar al párroco… Matías esbozó una breve sonrisa al recordar su trastada más repetida y famosa de la infancia, por la que su padre había agotado su repertorio de castigos. Le pareció escuchar el sonido bronco y seco de la pequeña explosión que transformaba el profundo silencio de la oscura iglesia en gritos de sorpresa y protesta. Luego llegaba la mirada de reproche —pero también divertida— de Attua y el arrepentimiento que lo hacía encogerse en su banco, con una actitud fingidamente serena, la mirada hacia el suelo, la blanda y amorfa vejiga escondida entre las manos, el labio superior temblándole ligeramente al ser consciente de que su audacia había sido más bien imprudencia. Entonces su padre, furioso, lo cogía por la oreja y lo sacaba de la iglesia. El último bando del alcalde dejaba bien claro que estaba prohibido reventar vejigas en el templo. ',7,7,1)," +
                    "(8,'Hugo y Damián se suponía que eran hermanos, se apellidaban Covarrubias y tenían la misma edad. Pero solo coincidían en eso. La madre de Damián vivía, la de Hugo no. Si a sus veinte años el primero sabía lo que quería hacer de su vida, el otro solo tenía claro lo que no iba a ser. Si Damián asentía, Hugo negaba. Si uno se apremiaba en cumplir lo que se le pedía, el otro se perdía en excusas. Quizá por eso y desde hacía un tiempo su padre había tomado partido. Nunca se lo había hecho saber, pero empezaba a ser evidente. Don Fernando de Covarrubias era hombre de linaje y uno de los comerciantes de lana más importantes de Castilla. Además, desde hacía siete años era el prior de la Universidad de Mercaderes de Burgos, la institución gremial que agrupaba y protegía los intereses de un selecto grupo de comerciantes dedicados a la venta del preciado vellón. Sin embargo, en los últimos años, ni su apellido ni la prestigiosa posición comercial que se había ganado con el tiempo compensaban su cansancio y el mal estado de sus arcas, y justo por eso aquel año no podía ser uno más dentro de sus habituales citas con la feria de Medina del Campo. Necesitaba con urgencia nuevos clientes para sus lanas, pero también frenar la pérdida de los actuales, y centenares de ellos pisaban las calles y plazas de Medina durante los cincuenta días que duraba la feria. Unos venidos desde Flandes, otros de Francia, Inglaterra o Lombardía; la ciudad castellana se convertía en el principal enclave europeo para el comercio de lana, tejidos, créditos, artesanía, especias y libros. Pero no solo necesitaba clientes y más negocio… Las campanas de la catedral de Burgos tocaban a misa cuando don Fernando cerró la ventana del palacio familiar situado frente al soberbio templo y miró a sus dos hijos, y supo que había llegado la hora. —Llevo cuarenta años sin descansar un solo día. Me he dejado la piel por mantener nuestro apellido en el lugar que le corresponde dentro del mercado de las lanas y, pese a haberse debilitado en los últimos años, los rendimientos de nuestro negocio aún permiten que viváis de él. Pero, hijos míos, ahora que empiezo a ser mayor me gustaría dedicar más tiempo a las fundaciones, a ver si las viera terminadas antes de morir, y sobre todo quiero descansar. Además, nuestro negocio precisa cambios, algunos muy urgentes, y se han de tomar importantes decisiones. —Su mirada viajó de un hijo a otro. Inspiró una profunda bocanada de aire antes de continuar—: Os he procurado un buen hogar, alimento y una educación esmerada. Por esta casa han pasado los mejores maestros para enseñaros las reglas de la geometría, de la matemática o de la ciencia en general. Os obligué a aprender la lengua de los ingleses, después de haberos enseñado la que se habla en Brujas, y conocéis un poco de francés, lo suficiente para poder comerciar en el norte de Europa. Me habéis acompañado durante los tres últimos años a la feria de Medina para aprender a cerrar tratos y conocer a los cambistas. Y si hasta ahora no os he pedido que me ayudarais en el trabajo fue por no frenar vuestra formación. Pero ha llegado la hora de cambiar todo eso. Hoy poseéis los conocimientos necesarios para empezar a tomar las riendas de nuestro negocio. ',8,8,1)," +
                    "(9,'Tras el intercambio habitual de fórmulas de cortesía, la sesión se había alargado media hora más y Brunetti empezaba a sufrir las consecuencias. Le habían pedido al hombre que tenían delante —un abogado de cuarenta y dos años cuyo padre era uno de los notarios de mayor éxito y, por consiguiente, con más poder de toda la ciudad— que acudiese esa mañana a la questura porque dos personas distintas lo habían nombrado como el individuo que dos días antes le había ofrecido unas pastillas a una chica en una fiesta que se había celebrado en un domicilio particular. La joven se las había tomado con un zumo de naranja que, según la información que había recibido la policía, también le había dado el mismo hombre. Un rato después se había desmayado y la habían llevado a urgencias del Ospedale Civile, donde había quedado ingresada con pronóstico reservado. Antonio Ruggieri había llegado puntual a las diez y, como muestra de su fe en las capacidades y probidad de la policía, no se había molestado en llevar consigo a otro abogado. Tampoco se había quejado del calor que hacía en aquella sala de ventana única, aunque había posado la mirada un instante en el ventilador de la esquina, que hacía lo que podía —si bien en vano— por contrarrestar el bochorno agobiante del mes de julio más caluroso del que se tenía constancia. Brunetti se había disculpado por la temperatura y le había explicado que la duración de la ola de calor había obligado a la questura a plantearse si destinar sus pobres recursos energéticos a los ordenadores o a encender el aire acondicionado, y se habían decantado por la primera opción. Ruggieri había sido cortés y se había limitado a preguntar si podía quitarse la chaqueta. Brunetti, que aún llevaba la suya, había empezado dejando del todo claro que se trataba de una conversación informal a fin de que les proporcionara información que los pusiera en antecedentes sobre lo ocurrido en la fiesta. ',9,9,1)," +
                    "(10,'Cuando yo tenía seis años vi en un libro sobre la selva virgen que se titulaba Historias vividas, una magnífica  lámina. Representaba una serpiente boa que se tragaba a una fiera. En el libro se afirmaba: ''La serpiente boa se traga su presa entera, sin masticarla. Luego ya no puede moverse y duerme durante los seis meses que dura su digestión''. Reflexioné mucho en ese momento sobre las aventuras de la jungla y a mi vez logré trazar con un lápiz de colores mi primer dibujo. Mi dibujo número 1 era de esta manera: Enseñé mi obra de arte a las personas mayores y les pregunté si mi dibujo les daba miedo. —¿por qué habría de asustar un sombrero?— me respondieron. Mi dibujo no representaba un sombrero. Representaba una serpiente boa que digiere un elefante. Dibujé entonces el interior de la serpiente boa a fin de que las personas mayores pudieran comprender. Siempre estas personas tienen necesidad de explicaciones. Mi dibujo número 2 era así: Las personas mayores me aconsejaron abandonar el dibujo de serpientes boas, ya fueran abiertas o cerradas, y poner más interés en la geografía, la historia, el cálculo y la gramática. De esta manera a la edad de seis años abandoné una magnífica carrera de pintor. Había quedado desilusionado por el fracaso de mis dibujos número 1 y número 2. Las personas mayores nunca pueden comprender algo por sí solas y es muy aburrido para los niños tener que darles una y otra vez explicaciones. Tuve, pues, que elegir otro oficio y aprendía pilotear aviones. He volado un poco por todo el mundo y la geografía, en efecto, me ha servido de mucho; al primer vistazo podía distinguir perfectamente la China de Arizona. Esto es muy útil, sobre todo si se pierde uno durante la noche. 2 A lo largo de mi vida he tenido multitud de contactos con multitud de gente seria. Viví mucho con personas mayores y las he conocido muy de cerca; pero esto no ha mejorado demasiado mi opinión sobre ellas. Cuando me he encontrado con alguien que me parecía un poco lúcido, lo he sometido a la experiencia de mi dibujo número 1 que he conservado siempre. Quería saber si verdaderamente era un ser comprensivo. E invariablemente me contestaban siempre: ''Es un sombrero''. Me abstenía de hablarles de la serpiente boa, de la selva virgen y de las estrellas. Poniéndome a su altura, les hablaba del bridge, del golf, de política y de corbatas. Y mi interlocutor se quedaba muy contento de conocer a un hombre tan razonable. ',10,10,1)," +
                    "(11,'Deyanira respiró con rapidez varias veces, tratando de reunir algo de fuerza en medio de aquel dolor inmisericorde. Olía a sudor y sangre. Hinchó los pulmones, elevando su pecho desnudo, y empujó de nuevo para que el bebé avanzara a través de sus entrañas. —Vas bien. El esfuerzo la hizo gruñir mientras miraba entre sus piernas abiertas a la mujer que tenía delante, sentada con expresión ceñuda a los pies de su lecho. Desplazó la mirada hacia la otra mujer que había en la alcoba. En una mano sostenía unos trapos limpios y con la otra acercaba una lámpara de aceite para que la partera hiciese su trabajo. Los ojos de la mujer rehuyeron los suyos. Deyanira se dejó caer jadeando sobre el colchón de lana, empapado con sus fluidos, y su vista se perdió en las penumbras del techo. «Protege a mi hijo, Ártemis Ortia, no dejes que le pase nada.» Aunque había visto mucha sangre, el parto estaba siendo más rápido que el primero. Habían transcurrido cuatro años, pero nunca olvidaría la resistencia del robusto Calícrates a abandonar su interior, como si se agarrara a sus tripas con sus manos regordetas. También recordaba la emoción que en aquel parto se respiraba a su alrededor, una alerta inquieta pero también alegre por asistir al milagro de dar a luz. En aquella ocasión, en algún lugar de su casa aguardaba orgulloso su marido Euxeno. «Mi difunto marido», se dijo con amargura. Cerró los ojos, deseando poder hacer que su hijo se quedara dentro de ella. Su cuerpo le indicó que tenía que seguir apretando, se irguió y al empujar notó que el bebé se deslizaba, un pez inocente abandonando sus aguas cálidas. La partera terminó de extraer al niño y los ojos grises de Deyanira se llenaron de lágrimas. «Ya no podré protegerte.» El bebé lloró débilmente, apenas una queja. Sus brazos tiritaban mientras la comadrona lo limpiaba y lo envolvía en una tela limpia. La ausencia de emoción en el rostro de la mujer llenaba de angustia el corazón de Deyanira, que alzó una mano hacia su hijo. La partera hizo un gesto a la otra mujer para que se ocupara de Deyanira y se giró hacia la puerta con el bebé en brazos. —¡No! —Deyanira intentó incorporarse en vano, había perdido demasiada sangre—. ¡Déjame verlo, déjame tocarlo! La mujer se detuvo. La miró y se volvió de nuevo hacia la puerta abierta. Sacudiendo la cabeza, se acercó a la cama y dejó al bebé en el pecho sudoroso de Deyanira, que se apresuró a besarlo. —Mi hijo. Mi bebé... El niño sacó una manita y la apoyó en la piel mojada de su madre. Su cabeza se movió hacia ambos lados como si olfateara con torpeza. Deyanira rozó con el dorso de un dedo su pequeña mandíbula y el recién nacido separó los párpados. Sus ojos eran grises como los de su madre, pero tan claros que parecían transparentes.',11,11,1)," +
                    "(12,'Contemplo el mar desde esta habitación. Ahora, todo me pertenece: la terraza que desciende poco a poco hacia las rocas, esos peldaños redondeados, las duchas exteriores, protegidas con unas losetas amarillas y azules en las que destacan unos limones dibujados a mano, el mármol situado delante del ventanal que refleja el horizonte. Alguna ola del mar, rebelde, todavía sin acostumbrarse a mi presencia, o tal vez para celebrar mi nueva llegada, rompe contra las rocas que mantienen la villa engarzada en esa espectacular parte elevada de la costa. El sol se está poniendo y su luz tiñe de rojo las paredes que están a mi espalda y las del salón. Exactamente igual que aquel día de hace nueve años. —¿Ha cambiado de idea? ¿Ya no quiere comprar la casa? El propietario me mira con aire interrogante. Luego abre los brazos sereno, sosegado, tranquilo. —Es libre de hacer lo que quiera, usted es quien paga. Pero si ya no está convencido, tendrá que darme el doble de las arras o meterse en uno de esos pleitos que, en vista de la edad que tengo, seguro que no me permitirán ver ni un céntimo. —Me lo quedo mirando divertido. El viejo señor es más avispado que un chiquillo. Frunce el ceño—. Claro que, si va usted con falsas intenciones, no le correrá prisa. Sin duda se saldrá con la suya, pasando por encima de mí, pero no de mis hijos o de mis nietos. ¡Ya sabe que en Italia los juicios pueden ir para largo! —Y una tos profunda y cansada lo asalta, obligándolo a cerrar los ojos y a acabar su sermón de último senador romano. Se toma un momento para recobrar el aliento, apoya la espalda en la butaca de tela, después se frota los ojos y los abre. —Pero usted quiere esta casa, ¿verdad? Me siento a su lado y cojo las hojas que tengo delante. Rubrico las páginas sin siquiera examinarlas; ya lo ha revisado todo mi abogado. Y estampo mi firma en la última página. —Entonces ¿la compra? —Sí, no he cambiado de idea, tengo lo que quería... El propietario recoge los documentos y se los pasa a su hombre de confianza. —Tengo que decirle la verdad: habría aceptado incluso la mitad del dinero. —Yo también quiero decirle la verdad: habría llegado a pagar el doble. Acto seguido, se levanta, se dirige hacia un mueble de madera antiguo y lo abre, saca una botella de champán de la nevera y, con algo de esfuerzo, la descorcha con verdadero placer y satisfacción. A continuación, lo sirve en dos copas altas. —¿En serio habría pagado el doble? —Sí. —¿No me lo dice para hacerme rabiar? —Y ¿por qué iba a hacer eso? Me cae bien, incluso me invita a tomar un champán excelente. —Mientras hablo, cojo la copa—. Y, además, a la temperatura perfecta, como a mí me gusta. No, en ningún caso quería hacerle rabiar. —Mmm. El propietario alza su copa hacia mí y hacia el cielo. —Ya le indiqué a mi abogado que podríamos haber pedido más... Me encojo de hombros y no digo nada, ni siquiera menciono los diez mil euros que le entregué a su abogado para persuadirlo de que aceptara la oferta. Noto sus ojos preocupados sobre mí, no sé en qué está pensando. Sacude la cabeza y sonríe convencido. ',12,12,1)," +
                    "(13,'Anne puede sentir el ácido revolviéndose en su estómago y trepando por su garganta; la cabeza le da vueltas. Ha bebido demasiado.Cynthia se ha pasado la noche rellenándole la copa. Anne no quería sobrepasar un límite, pero las cosas se le han ido de las manos; tampoco sabía de qué otra manera aguantar la velada. Ahora no tiene ni idea de cuánto vino ha bebido en el curso de esta interminable cena. Tendrá que extraerse leche por la mañana.\n Anne languidece en el calor de la noche de verano y observa a su anfitriona con los ojos entornados. Cynthia está coqueteando abiertamente con su marido, Marco. ¿Por qué lo aguanta Anne? ¿Y por qué lo permite el marido de Cynthia, Graham? Está enfadada, pero se siente impotente; no sabe cómo ponerle fin sin parecer patética y ridícula.Todos se encuentran un poco borrachos. Así que lo ignora, ardiendo silenciosamente de ira, y da otro sorbo a su vino frío. No la educaron para montar escenas, y tampoco le gusta llamar la atención. Sin embargo, Cynthia…\\\\nLos tres —Anne, Marco y el amable y blando esposo de Cynthia, Graham— la observan fascinados. Especialmente Marco parece incapaz de apartar los ojos de Cynthia. Cada vez que se inclina para rellenarle la copa se acerca un poco de más, y, como lleva una camiseta ceñida muy escotada, Marco le frota la nariz prácticamente contra los pechos.\n Anne se dice a sí misma que Cynthia coquetea con todo el mundo. Es tan despampanante y atractiva que parece incapaz de evitarlo.\n Sin embargo, cuanto más les mira, más se pregunta si de veras hay algo entre Marco y Cynthia. Nunca antes había sospechado. Tal vez sea el alcohol, que la está volviendo paranoica. No, decide: si tuvieran algo que esconder, no se andarían con estas. Cynthia tontea más que Marco, halagado beneficiario de sus atenciones. De hecho, él mismo es casi demasiado atractivo, con su pelo oscuro despeinado, esos ojos de color de avellana y su encantadora sonrisa; siempre ha llamado la atención. Hacen una pareja imponente, Cynthia y Marco. Anne se dice que ya basta. Que por supuesto que Marco le es fiel. Sabe que está completamente entregado a su familia. La niña y ella lo son todo para él. Estará a su lado, pase lo que pase —le da otro trago al vino—, por muy mal que se pongan las cosas.',13,13,1),(14,'El ambiente en el juzgado era irrespirable. La humedad de la lluvia, prendida en los abrigos, comenzaba a evaporarse, mezclada con el aliento de cientos de personas que abarrotaban los pasillos frente a las distintas salas. Amaia se desabrochó el chaquetón mientras saludaba al teniente Padua, que, tras hablar brevemente con la mujer que lo acompañaba e instándola a entrar en la sala, se acercó sorteando a la gente que esperaba. —Inspectora, me alegro de verla. ¿Cómo se encuentra? No estaba seguro de que pudiera estar aquí hoy —dijo, con un gesto hacia el abultado vientre. Ella se llevó una mano a la tripa, que evidenciaba el último tramo del embarazo. —Bueno, parece que de momento aguantará. ¿Ha visto a la madre de Johana? —Sí, está bastante nerviosa. Espera dentro acompañada por su familia, acaban de llamarme de abajo para decir que ha llegado el furgón que trae a Jasón Medina —dijo dirigiéndose al ascensor. Amaia entró en la sala y se sentó en uno de los bancos del final; aun así veía a la madre de Johana Márquez, enlutada y mucho más delgada que en el funeral de la niña. Como si percibiese su presencia, la mujer se volvió a mirar y la saludó con un breve gesto de asentimiento. Amaia intentó sonreír, sin conseguirlo, mientras apreciaba la apariencia lavada del rostro de aquella madre atormentada por la certeza de no haber podido proteger a su hija del monstruo que ella misma había llevado a casa. El secretario procedió a leer en voz alta los nombres de los citados. No se le escapó el gesto de crispación que se dibujó en la cara de la mujer al escuchar el nombre de su marido. —Jasón Medina —repitió el secretario—. Jasón Medina. Un policía de uniforme entró corriendo en la sala, se acercó al secretario y le susurró algo al oído. A su vez se inclinó para hablar con el juez, que escuchó sus palabras, asintió, llamó al fiscal y a la defensa, les habló brevemente y se puso en pie. —Se suspende la sesión, serán citados nuevamente si así procede. —Y sin decir más, salió de la sala. La madre de Johana comenzó a gritar mientras se volvía hacia ella demandando respuestas. —No —chilló—, ¿por qué? Las mujeres que la acompañaban intentaron en vano abrazarla para contener su desesperación. Uno de los policías se acercó a Amaia. —Inspectora Salazar, el teniente Padua le pide que baje a los calabozos. ',14,14,1),(15,'El mar estaba embravecido; el cielo, gris plomizo. En la playa, la gente de las atarazanas, los barqueros, los marineros y los bastaixos permanecían en tensión; muchos se frotaban las manos o daban palmadas para calentárselas mientras que otros trataban de protegerse del viento gélido. Casi todos estaban en silencio, mirándose entre ellos antes de hacerlo a las olas que rompían con fuerza. La imponente galera real de treinta bancos de remeros por banda se hallaba a merced del temporal. Durante los días anteriores los mestres d’aixa de las atarazanas, ayudados por aprendices y marineros, habían procedido a desmontar todos los aparejos y elementos accesorios del navío: timones, armamento, velas, mástiles, bancos, remos… Los barqueros habían llevado todo aquello que se podía separar del barco hasta la playa, donde fue recogido por los bastaixos, quienes lo transportaron a sus correspondientes almacenes. Se dejaron tres anclas que eran las que, aferradas al fondo, tironeaban ahora de la Santa Marta, un imponente armazón desvalido contra el que batía el oleaje. Hugo, un muchacho de doce años con el cabello castaño, y las manos y el rostro tan sucios como la camisa que vestía hasta las rodillas, mantenía clavados sus ojos de mirada inteligente en la galera. Desde que trabajaba con el genovés en las atarazanas había ayudado a varar y a echar a la mar bastantes como aquella, pero esa era muy grande y el temporal hacía peligrar la operación. Algunos marineros deberían embarcar en la Santa Marta para desanclarla y luego los barqueros tendrían que remolcarla hasta la playa, donde un enjambre de herederos de la tierra, personas la esperaba para arrastrarla hasta el interior de las atarazanas. Allí hibernaría. Se trataba de una labor ardua y, sobre todo, extremadamente dura, incluso haciendo uso de las poleas y los cabestrantes de que se servían para tirar de la nave una vez varada en la arena. Barcelona, pese a ser una de las potencias marítimas del Mediterráneo junto con Génova, Pisa y Venecia, no tenía puerto; no existían refugios ni diques que facilitasen las tareas. Barcelona era toda ella playa abierta. —Anemmu, Hugo —ordenó el genovés al muchacho. Hugo miró al mestre d’aixa. —Pero… —intentó alegar. —No discutas —le interrumpió el genovés—. El lugarteniente de las atarazanas —añadió señalando con el mentón hacia un grupo de hombres que se hallaba algo más allá— acaba de dar la mano al prohombre de la cofradía de los barqueros. Eso significa que han llegado a un acuerdo sobre el nuevo precio que el rey les pagará como consecuencia del peligro añadido por el temporal. ¡La sacaremos del agua! Anemmu —repitió. Hugo se agachó y agarró la bola de hierro que permanecía unida mediante una cadena al tobillo derecho del genovés y, no sin esfuerzo, la alzó y se la pegó al vientre. —¿Estás listo?  —inquirió el genovés. —Sí. —El maestro mayor nos espera. El muchacho cargó con la bola de hierro que impedía moverse al mestre d’aixa. Tras él anduvo por la playa y discurrió entre las gentes que, ya apercibidas del trato, charlaban, gritaban, señalaban y volvían a gritar, nerviosas, a la espera de las instrucciones del maestro mayor. Entre todos ellos se contaban más genoveses, también hechos prisioneros en el mar e inmovilizados con bolas de hierro, cada uno con otro chico a su lado que la sostenía en sus brazos mientras ellos trabajaban forzados en las atarazanas catalanas ',15,15,1);";
    private static final String INSERT_QUESTIONS =
            "INSERT INTO question(content,ntext,ans1,ans2,ans3,ans4) VALUES ('¿Que esta bebiendo el hombre?',1,'Doble Whisky','Whisky','Tequila','Agua')," +
            "('¿Cuanto tiempo falta para la hora del cierre?',1,'15 minutos','25 minutos','30 minutos','No lo recuerdo')," +
            "('¿Quien es el autor del libro?',1,'Jojo Moyes','José Rene Franco','J.K. Rowling','Mario Vargas Luna')," +
            "('¿Cual es titulo del libro?',1,'Después de ti','Botellas de whisky','Historia de un canalla','Cinco esquinas')," +
            "('¿Cual es el nombre de la primera dama que se cita?',2,'Chabela' ,'Mariana','Luisa','Juana')," +
            "('¿Cuantos personajes se encuentran en el texto?',2,'4','5','3','6')," +
            "('¿Cual era el nombre del esposo de Chabela?',2,'Luciano','Mariano','Juan','José')," +
            "('¿Quien es el autor del libro?',2,'Mario Vargas Llosa','Miguel Francisco Leines','Julián Hernández','Juan de la Rosa')," +
            "('¿Cual es el nombre de la persona que cuidaba a los niños?',3,'María','Ana','Ramona','Abigail')," +
            "('¿Cual es el titulo del libro?',3,'Historia de un canalla','Historia de mi vida','Un canalla en la ciudad','Siempre hay que terminar lo que se empezó')," +
            "('¿Cual fue el motivo de las quejas de la niñera?',3,'Que el protagonista desobedeciera y se soltara de ella','Que no se le hiciera caso','Que el protagonista no tuviera cuidado','Que su madre no le hiciera caso')," +
            "('¿Cual es la edad aproximada del protagonista?',3,'7 o 8 años','8 o 9 años','10 o 11 años','4 o 5 años')," +
            "('¿Cual es el nombre de la madre de Nerea?',4,'Bittori','Victoria','Fernanda','Clara')," +
            "('¿Cual era la expresion que notaba la madre de Nerea?',4,'Tristeza','Ira','Indiferencia','Felicidad')," +
            "('¿Cual es la edad de la mujer?',4,'45 años','44 años','40 años','35 años')," +
            "('¿Cual es el nombre de la hija?',4,'Nerea','Nervea','Ana Luisa','Ana Perla')," +
            "('¿Cual es el apellido de Manuel?',5,'Mena','Meneses','Montalvo','Perez')," +
            "('¿A que edad murió Manuel?',5,'19 años','18 años','20 años','17 años')," +
            "('¿Cual es el nombre del pueblo catalán?',5,'Bot','Botel','Cataluña','Madrid')," +
            "('¿Cual es el nombre del autor del libro?',5,'Javier Cercas','Javier Casas','José Cercas','Mario Luna')," +
            "('¿Como se llama el cementerio?',6,'Cementerio de los libros olvidados','Cementerio de las personas olvidadas','Cementerio de los libros encontrados','Cementerio municipal')," +
            "('¿Quien acompaño al personaje hacia el portal?',6,'Su padre','Su madre','Su hijo','Nadie')," +
            "('¿Cuando su padre soltaba la mano del protagonista, que mensaje le daba a entender?',6,'Que era un camino que tenia que recorrer solo','Que no era adecuado que el fuera tambien','Que no era momento de ir','Que era un camino peligroso')," +
            "('¿El padre le consolaba por una pesadilla sobre?',6,'El abandono de su madre','La muerte de su madre','Los golpes de su madre','Un monstruo')," +
            "('¿Cual es el nombre de la persona a la cual quería convencer Attua?',7,'Matías','Mario','Marco','Manuel')," +
            "('¿Quien corría peligro en el duelo?',7,'El mejor amigo de Matías','El amigo de Attua','El padre de Matías','El perro de Matías')," +
            "('¿Cual era la actitud de Matías ante la insistencia de Attua?',7,'Arrogancia','Furia','Nervios','Miedo')," +
            "('¿Que fue lo que le hizo recordar Attua?',7,'Una travesura que hizo de niño en la iglesia','Una travesura que le hizo de niño al alcalde','Su infancia en la iglesia','Su infancia con el alcalde');";


    public ArrayList<String> getTextContent(){
        ArrayList<String> lista = new ArrayList<>();
        String book;
        String author;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT books.book, books.author, texts.leveltxt FROM texts,books WHERE books.id_book = texts.nbook",null);
        if (c.moveToFirst()){
            do {
                Log.e("Done","obteniendo datos--------------------------------------------");
                book = c.getString(0);
                author = c.getString(1);
                lista.add(book);
            }while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    public ArrayList<String> getQuestionsBook(String book){
        ArrayList<String> lista = new ArrayList<>();
        String question;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{book};
        Cursor c = db.rawQuery("SELECT question.content, question.ans1, question.ans2, question.ans3, question.ans4 FROM books, texts, question WHERE books.id_book = texts.nbook AND texts.id_text = question.ntext AND book = ? ",args);
        if (c.moveToFirst()){
            do {
                Log.e("Done","obteniendo datos--------------------------------------------");
                question = c.getString(0);
                lista.add(question);
            }while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    public ArrayList<String> getAnswers(String question) {
        ArrayList<String> lista = new ArrayList<>();
        String answer1, answer2, answer3, answer4;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{question};
        Cursor c = db.rawQuery("SELECT ans1, ans2, ans3, ans4 FROM question WHERE content = ?", args);
        if (c.moveToFirst()) {
            do {
                Log.e("Done", "obteniendo datos--------------------------------------------");
                answer1 = c.getString(0);
                answer2 = c.getString(1);
                answer3 = c.getString(2);
                answer4 = c.getString(3);
                lista.add(answer1);
                lista.add(answer2);
                lista.add(answer3);
                lista.add(answer4);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

}
