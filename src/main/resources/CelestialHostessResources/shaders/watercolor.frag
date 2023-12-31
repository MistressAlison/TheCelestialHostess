#ifdef GL_ES
#define LOWP
precision mediump float;
#else
#define LOWP
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
//varying vec4 v_pos;
//varying vec4 v_apos;

uniform sampler2D u_texture;
uniform float u_scale;//settings dot scale
uniform vec2 u_screenSize;//width, height
uniform vec2 u_mouse;
uniform float x_time;

#define PI 3.14159265358979

vec4 getCol(vec2 pos)
{
    vec4 c1 = texture(u_texture, v_texCoords);
    vec4 c2 = vec4(.4); // gray on greenscreen
    float d = clamp(dot(c1.xyz,vec3(-0.5,1.0,-0.5)),0.0,1.0);
    return mix(c1,c2,1.8*d);
}

vec4 getCol2(vec2 pos)
{
    vec4 c1 = texture(u_texture, v_texCoords);
    vec4 c2 = vec4(1.5); // bright white on greenscreen
    float d = clamp(dot(c1.xyz,vec3(-0.5,1.0,-0.5)),0.0,1.0);
    return mix(c1,c2,1.8*d);
}

vec2 getGrad(vec2 pos,float delta)
{
    vec2 d=vec2(delta,0.f);
    float f1 = dot((getCol(pos+d.xy)-getCol(pos-d.xy)).xyz,vec3(.333));
    float f2 = dot((getCol(pos+d.yx)-getCol(pos-d.yx)).xyz,vec3(.333));
    return vec2(f1, f2)/delta;
}

vec2 getGrad2(vec2 pos,float delta)
{
    vec2 d=vec2(delta,0.f);
    float f1 = dot((getCol2(pos+d.xy)-getCol2(pos-d.xy)).xyz,vec3(.333));
    float f2 = dot((getCol2(pos+d.yx)-getCol2(pos-d.yx)).xyz,vec3(.333));
    return vec2(f1, f2)/delta;
}

vec4 getRand(vec2 pos)
{
    return vec4(0.);
}

float htPattern(vec2 pos)
{
    float p;
    float r=getRand(pos*.4/.7*1.).x;
    p=clamp((pow(r+.3,2.)-.45),0.,1.);
    return p;
}

float getVal(vec2 pos, float level)
{
    return length(getCol(pos).xyz)+0.0001*length(pos-0.5*u_screenSize);
    return dot(getCol(pos).xyz,vec3(.333));
}

vec4 getBWDist(vec2 pos)
{
    return vec4(smoothstep(.9,1.1,getVal(pos,0.)*.9+htPattern(pos*.7)));
}

#define SampNum 24

//#define N(a) (a.yx*vec2(1,-1))

vec2 N(vec2 a)
{
    return a.yx*vec2(1,-1);
}

void main()
{
    vec2 pos=((v_texCoords-u_screenSize.xy*.5)/u_screenSize.y);
    vec2 pos2=pos;
    vec2 pos3=pos;
    vec2 pos4=pos;
    vec2 pos0=pos;
    vec3 col=vec3(0);
    vec3 col2=vec3(0);
    float cnt=0.0;
    float cnt2=0.;
    for(int i=0;i<1*SampNum;i++)
    {
        // gradient for outlines (gray on green screen)
        vec2 gr =getGrad(pos, 2.0)+.0001*(getRand(pos ).xy-.5);
        vec2 gr2=getGrad(pos2,2.0)+.0001*(getRand(pos2).xy-.5);

        // gradient for wash effect (white on green screen)
        vec2 gr3=getGrad2(pos3,2.0)+.0001*(getRand(pos3).xy-.5);
        vec2 gr4=getGrad2(pos4,2.0)+.0001*(getRand(pos4).xy-.5);

        float grl=clamp(10.*length(gr),0.,1.);
        float gr2l=clamp(10.*length(gr2),0.,1.);

        // outlines:
        // stroke perpendicular to gradient
        pos +=.8 *normalize(N(gr));
        pos2-=.8 *normalize(N(gr2));
        float fact=1.-float(i)/float(SampNum);
        col+=fact*mix(vec3(1.2),getBWDist(pos).xyz*2.,grl);
        col+=fact*mix(vec3(1.2),getBWDist(pos2).xyz*2.,gr2l);

        // colors + wash effect on gradients:
        // color gets lost from dark areas
        pos3+=.25*normalize(gr3)+.5*(getRand(pos0*.07).xy-.5);
        // to bright areas
        pos4-=.5 *normalize(gr4)+.5*(getRand(pos0*.07).xy-.5);

        float f1=3.*fact;
        float f2=4.*(.7-fact);
        col2+=f1*(getCol2(pos3).xyz+.25+.4*getRand(pos3*1.).xyz);
        col2+=f2*(getCol2(pos4).xyz+.25+.4*getRand(pos4*1.).xyz);

        cnt2+=f1+f2;
        cnt+=fact;
    }
    // normalize
    col/=cnt*2.5;
    col2/=cnt2*1.65;

    // outline + color
    col = clamp(clamp(col*.9+.1,0.,1.)*col2,0.,1.);
    // paper color and grain
    col = col*vec3(.93,0.93,0.85)
    //*mix(texture(iChannel2,fragCoord.xy/iResolution.xy).xyz,vec3(1.2),.7)
    +.15*getRand(pos0*2.5).x;
    // vignetting
    float r = length((v_texCoords-u_screenSize.xy*.5)/u_screenSize.x);
    float vign = 1.-r*r*r*r;

    gl_FragColor = vec4(col*vign,1.0);
}