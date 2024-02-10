<helpset version="1.0">
    <title>Manual de Aplicacion</title>
    <maps>
        <homeID> Login </homeID>
        <mapref location="mapa.jhm"></mapref>
    </maps>
    <view>
        <name>Tabla de Contenido</name>
        <label>Tabla de contenidos</label>
        <type>javax.help.TOCView</type>
        <data>toc.xml</data>
    </view>
    <view>
        <name>Indice</name>
        <label>Indice</label>
        <type>javax.help.IndexView</type>
        <data>indice.xml</data>
    </view>
    <view>
        <name>Buscador</name>
        <label>Buscador</label>
        <type>javax.help.SearchView</type>
        <data engine="com.sun.java.help.search.DefaultSearchEngine">Java Help</data>
    </view>
</helpset>