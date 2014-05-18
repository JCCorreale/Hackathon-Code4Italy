package it.camera.hackathon.datasource;


public interface IDataSourceAdapter<T, A> {
	IDataSource<T> getDataSource(IDataProvider<T, A> dataProvider, A fixedProviderArg);
}
