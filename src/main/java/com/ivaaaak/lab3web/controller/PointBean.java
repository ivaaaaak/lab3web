package com.ivaaaak.lab3web.controller;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ivaaaak.lab3web.service.Service;
import lombok.Data;
import java.io.Serializable;
import java.util.Map;

@Data
@Named
@ViewScoped
public class PointBean implements Serializable {

    @Inject
    Service service;

    private double x;
    private double y;
    private double r;

    public void submit() {
        service.checkHit(x, y, r);
    }

    public void clear() {
        service.clear();
    }

    public void submitWithJs() {
        final Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            x = Double.parseDouble(params.get("x"));
            y = Double.parseDouble(params.get("y"));
            r = Double.parseDouble(params.get("r"));
            submit();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
