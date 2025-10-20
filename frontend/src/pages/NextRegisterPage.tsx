import { Link } from "react-router-dom";
import Section from "../componets/Section";
import H1 from "../componets/H1";
import H2 from "../componets/H2";
import Label from "../componets/Label";
import Input from "../componets/Input";
import Button from "../componets/Button";
import Select from "../componets/Select";


const RegisterPage = () => {
  return (
    <Section>
      <H1>Registro</H1>
      <H2>Tec-Match</H2>

      <form>
        <div className="mb-4 text-center">
          <Label htmlFor="username">Sexo</Label>
          <Select id="sexo" name="sexo">
            <option value="hombre">Hombre</option>
            <option value="mujer">Mujer</option>
          </Select>
        </div>

        <div className="mb-4 text-center">
          <Label htmlFor="degree">Carrera</Label>
          <Select id="degree" name="degree">
            // Licenciaturas
            <option value="la">LA</option>
            <option value="laet">LAET</option>
            <option value="lae">LAE</option>
            <option value="larq">LARQ</option>
            <option value="lce">LCE</option>
            <option value="lcef">LCEF</option>
            <option value="lcp">LCP</option>
            <option value="lcpmi">LCPM</option>
            <option value="ldcfd">LDCFD</option>
            <option value="ldg">LDG</option>
            <option value="ld">LD</option>
            <option value="lef">LEF</option>
            <option value="leagc">LEAGC</option>
            <option value="lei">LEI</option>
            <option value="leigi">LEIGI</option>
            <option value="lei2">LEI</option>
            <option value="lenf">LENF</option>
            <option value="lg">LG</option>
            <option value="lm">LM</option>
            <option value="lp">LP</option>
            <option value="lta">LTA</option>
            // Ingenierias
            <option value="ib">IB</option>
            <option value="ibt">IBT</option>
            <option value="ica">ICA</option>
            <option value="ic">IC</option>
            <option value="ie">IE</option>
            <option value="iel">IEL</option>
            <option value="iis">IIS</option>
            <option value="il">IL</option>
            <option value="im">IM</option>
            <option value="imt">IMT</option>
            <option value="iq">IQ</option>
            <option value="isw">ISW</option>
            <option value="mvz">MVZ</option>
            // Profesional Asociado
            <option value="pa-di">PA-DI</option>
            // Especialidades
            <option value="e-cag">E-CAG</option>
            // Maestrias
            <option value="mati">MATI</option>
            <option value="madn">MADN</option>
            <option value="mci">MCI</option>
            <option value="mciq">MCIQ</option>
            <option value="mcrn">MCRN</option>
            <option value="meb">MEB</option>
            <option value="me">ME</option>
            <option value="meigi">MEIGI</option>
            <option value="mef">MEF</option>
            <option value="mf">MF</option>
            <option value="mgfn">MGFN</option>
            <option value="mie">MIE</option>
            <option value="mia">MIA</option>
            <option value="milc">MILC</option>
            <option value="mia-cs">MIA-CS</option>
            <option value="mie2">MIE</option>
            <option value="mip">MIP</option>
            <option value="mme">MME</option>
            <option value="mp">MP</option>
            <option value="mtin">MTIN</option>
            <option value="mv">MV</option>
            // Doctorado
            <option value="dci">DCI</option>
            <option value="dceb">DCEB</option>
            <option value="de">DE</option>
            <option value="dge">DGE</option>
            <option value="dip">DIP</option>
          </Select>
        </div>

        <div className="mb-4 text-center">
          <Label htmlFor="description">Descripción</Label>
          <Input id="description" type="text" placeholder="Hablanos sobre ti" />
        </div>

        <div className="mt-14 relative w-full border-b-2 border-black my-6"></div>

        <Link to={"/registerPage"}>
          <Button>&lt;</Button>
        </Link>

        <Link to={"/hobbiesPage"}>
          <Button>Continuar</Button>
        </Link>
      </form>
    </Section>
  );
};

export default RegisterPage;
