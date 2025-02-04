package zut.base.dao;

import zut.base.domain.BaseTreeEntity;

import java.io.Serializable;

public class GenericTreeDaoTestCase<PK extends Serializable, T extends BaseTreeEntity<T>, M extends GenericTreeDao<T, PK>>
        extends GenericDaoTestCase<PK, T, M> {
}
