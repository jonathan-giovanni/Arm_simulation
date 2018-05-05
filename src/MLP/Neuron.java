package MLP;


public class Neuron {

    private float activation;
    private float[] synapticWeights;
    static final float lambda=2f;

    public Neuron(int previous_n_neurons,java.util.Random rand){
        synapticWeights=new float[previous_n_neurons];

        for(int i=0;i<previous_n_neurons;++i){
            synapticWeights[i]=rand.nextFloat()-0.5f;
        }

    }

    public float activate(float inputs[]){
        activation=0.0f;
        assert(inputs.length == synapticWeights.length);

        for(int i=0;i<inputs.length;i++)
            activation+=inputs[i]*synapticWeights[i];

        return 2.0f / (1.0f + (float) Math.exp((-activation) * lambda)) - 1.0f;

    }

    public float getActivationDerivative(){
        float exmplx= 2.0f / (1.0f + (float) Math.exp((-activation) * lambda)) - 1.0f;

        return 1-(exmplx*exmplx);
    }

    public float[] getSynapticWeights() {
        return synapticWeights;
    }

    public float getSynapticWeights(int i) {
        return synapticWeights[i];
    }


	/*public void setSynapticWeights(float[] synapticWeights) {
		this.synapticWeights = synapticWeights;
	}*/


    public void setSynapticWeights(int i,float synapticWeight) {
        this.synapticWeights[i] = synapticWeight;
    }



}