import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

// material-ui
import { ButtonBase, Typography } from '@mui/material';
import logoimg from '../../assets/images/icons/logo.png';
// project import
import Logo from './Logo';
import config from 'config';
import Google from '../../assets/images/icons/google.svg';

// ==============================|| MAIN LOGO ||============================== //

const LogoSection = ({ sx, to }) => (
    <ButtonBase disableRipple component={Link} to={!to ? config.defaultPath : to} sx={sx}>
        <img src={logoimg} alt='logo' height={50} />
        <Typography variant='h6' align='center'>
            SNOC
        </Typography>
    </ButtonBase>
);

LogoSection.propTypes = {
    sx: PropTypes.object,
    to: PropTypes.string
};

export default LogoSection;
