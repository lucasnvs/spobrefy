package com.spobrefy.hur;

public interface IUpgradeTo<X, Y> {

    Y upgrade(X obj, String cpf, String birthDate);
}
