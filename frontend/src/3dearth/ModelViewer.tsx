import React, { Suspense } from "react";
import { Canvas } from "@react-three/fiber";
import { OrbitControls, useGLTF } from "@react-three/drei";
import '@google/model-viewer/lib/model-viewer'

function Model() {
  const { scene } = useGLTF("/planet.glb")
  return <primitive object={scene} />;
}

const ModelViewer = () => {
  return (
    <div style = {{height:"100vh", backgroundColor:"#231955"}}>
      <Canvas>
        <ambientLight intensity={0.3} />
        <spotLight position={[10, 10, 10]} angle={0.15} penumbra={1} />
        <pointLight position={[-10, -10, -10]}/>
        <Suspense fallback={null}>
          <Model/>
        </Suspense>
        <OrbitControls />
      </Canvas>
    </div>
  );
};

export default ModelViewer;