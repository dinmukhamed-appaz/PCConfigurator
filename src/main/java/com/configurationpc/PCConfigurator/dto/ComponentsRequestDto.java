package com.configurationpc.PCConfigurator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentsRequestDto{

    private String name;
    private String category;
    private Double price;


    //Case
    private String supportedFormFactor;
    private Integer maxGpuLength;
    private Integer maxCoolerHeight;

    //Cooler
    private String supportedSockets;
    private Integer maxTdp;
    private Integer heightCooler;

    //Cpu
    private Integer tdp;

    //Gpu
    private Integer powerConsumption;
    private String powerConnectors;
    private Integer lengthGpu;

    //Motherboard/Cpu
    private String socket;

    //Motherboard
    private String ramType;
    private String formFactor;

    //Psu
    private Integer totalPower;
    private String availableConnectors;

    //Ram
    private Integer memory;




}