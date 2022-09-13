/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author antiapgg
 */
@Named
@RequestScoped
public class ImagesView {

    private List<String> images;

    public ImagesView(){
        images = new ArrayList<String>();
        images.add("https://wp-growpro.s3-eu-west-1.amazonaws.com/media/2020/08/viajes-para-aprender-ingles.jpg");
        images.add("https://micarreralaboralenit.files.wordpress.com/2018/10/viaje-de-trabajo.jpg?w=740");
        images.add("https://aventurateaviajar.com/library/mod_noticias/images/aventurate-a-viajar.JPG");
        images.add("https://viajes.nationalgeographic.com.es/medio/2018/10/09/catapulta-nevis_ac73057e_1440x810.jpg");
        images.add("https://aws.traveler.es/prod/designs/v1/assets/940x629/135662.jpg");
        images.add("https://cdn.aarp.net/content/dam/aarp/travel/local-getaways/2018/10/1140-couple-outdoors-tree-esp.jpg");
        images.add("https://cdn.elnacional.com/wp-content/uploads/2018/07/organizar-planificar-viaje-familiar_243109.jpg");
        images.add("https://imagenes.milenio.com/wXdo2M7TELVtk4y880F_VqWof1I=/958x596/https://www.milenio.com/uploads/media/2019/04/11/viaje-en-familia.jpg");
        images.add("https://www.hola.com/imagenes/novias/2015012176349/luna-de-miel-destinos-playa/0-304-798/novias-playa-01-a.jpg");
    }
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        /*images.add("https://wp-growpro.s3-eu-west-1.amazonaws.com/media/2020/08/viajes-para-aprender-ingles.jpg");
        images.add("https://micarreralaboralenit.files.wordpress.com/2018/10/viaje-de-trabajo.jpg?w=740");
        images.add("https://aventurateaviajar.com/library/mod_noticias/images/aventurate-a-viajar.JPG");
        images.add("https://viajes.nationalgeographic.com.es/medio/2018/10/09/catapulta-nevis_ac73057e_1440x810.jpg");
        images.add("https://aws.traveler.es/prod/designs/v1/assets/940x629/135662.jpg");
        images.add("https://cdn.aarp.net/content/dam/aarp/travel/local-getaways/2018/10/1140-couple-outdoors-tree-esp.jpg");
        images.add("https://cdn.elnacional.com/wp-content/uploads/2018/07/organizar-planificar-viaje-familiar_243109.jpg");
        images.add("https://imagenes.milenio.com/wXdo2M7TELVtk4y880F_VqWof1I=/958x596/https://www.milenio.com/uploads/media/2019/04/11/viaje-en-familia.jpg");
        images.add("https://www.hola.com/imagenes/novias/2015012176349/luna-de-miel-destinos-playa/0-304-798/novias-playa-01-a.jpg");*/
        for (int i = 1; i <= 10; i++) {
            images.add(i + ".jpeg");
        }
    }

    public List<String> getImages() {
        return images;
    }
}
